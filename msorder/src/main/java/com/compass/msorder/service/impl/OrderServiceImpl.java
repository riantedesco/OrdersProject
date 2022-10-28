package com.compass.msorder.service.impl;

import com.compass.msorder.domain.ClientEntity;
import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.domain.ProductOrderEntity;
import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.ProductOrderDto;
import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.domain.dto.form.OrderUpdateFormDto;
import com.compass.msorder.domain.dto.form.ProductOrderFormDto;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.publisher.payment.dto.PaymentPublisherDto;
import com.compass.msorder.repository.ClientRepository;
import com.compass.msorder.repository.OrderRepository;
import com.compass.msorder.service.OrderService;
import com.compass.msorder.service.ProductOrderService;
import com.compass.msorder.util.constants.RabbitMQConstants;
import com.compass.msorder.util.constants.StatusOrderOption;
import com.compass.msorder.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ProductOrderService productOrderService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public OrderDto save(OrderFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		OrderEntity order = mapper.map(body, OrderEntity.class);

		if (body.getIdClient() != null) {
			Optional<ClientEntity> client = this.clientRepository.findById(body.getIdClient());
			if(!client.isPresent()) {
				throw new NotFoundAttributeException("Client not found");
			}
			order.setClient(client.get());
		}

		if (!body.getProductOrders().isEmpty()) {
			List<ProductOrderFormDto> listProductOrder = body.getProductOrders();
			for(ProductOrderFormDto po : listProductOrder) {
				ProductOrderEntity productOrder = mapper.map(po, ProductOrderEntity.class);
				ProductOrderDto productOrderResponse = this.productOrderService.save(po);
				order.setTotal(order.getTotal() + productOrderResponse.getTotal());

			}
		}

		order.setStatus(StatusOrderOption.ORDER_CREATED);
		validation.validateOrder(order);

		PaymentPublisherDto paymentPublisherDto = new PaymentPublisherDto(order.getId(), order.getClient().getCpf(), order.getTotal(), order.getStatus());
		rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.ORDER_NOTIFICATION_ROUTINGKEY_NAME, paymentPublisherDto);

		OrderEntity orderResponse = this.orderRepository.save(order);
		return mapper.map(orderResponse, OrderDto.class);
	}

	@Override
	public OrderDto findByIdNumberAndCpfClient(Long id, Long number, String cpfClient) {
		Optional<OrderEntity> order = this.orderRepository.findByIdNumberAndCpfClient(id, number, cpfClient);
		if (!order.isPresent()) {
			throw new NotFoundAttributeException("Order not found");
		}
		return mapper.map(order.get(), OrderDto.class);
	}

	@Override
	public OrderDto update(Long id, OrderUpdateFormDto body) {
		Optional<OrderEntity> order = this.orderRepository.findById(id);
		if (!order.isPresent()) {
			throw new NotFoundAttributeException("Order not found");
		}
		order.get().setNumber(body.getNumber());

		Optional<ClientEntity> client = this.clientRepository.findById(body.getIdClient());
		if (!client.isPresent()) {
			throw new NotFoundAttributeException("Client not found");
		}
		order.get().setClient(client.get());

		validation.validateOrder(order.get());
		OrderEntity o = this.orderRepository.save(order.get());
		return mapper.map(o, OrderDto.class);
	}

}
