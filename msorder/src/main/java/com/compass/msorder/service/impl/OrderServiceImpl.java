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
import com.compass.msorder.publisher.payment.PaymentPublisher;
import com.compass.msorder.repository.ClientRepository;
import com.compass.msorder.repository.OrderRepository;
import com.compass.msorder.service.OrderService;
import com.compass.msorder.service.ProductOrderService;
import com.compass.msorder.util.constants.StatusOrderOption;
import com.compass.msorder.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PaymentPublisher paymentPublisher;

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
		order.setId(null);

		if (body.getIdClient() != null) {
			Optional<ClientEntity> client = this.clientRepository.findById(body.getIdClient());
			if(!client.isPresent()) {
				throw new NotFoundAttributeException("Client not found");
			}
			order.setClient(client.get());
		}

		order.setStatus(StatusOrderOption.ORDER_CREATED);
		validation.validateOrder(order);
		OrderEntity orderResponse = this.orderRepository.save(order);
		OrderDto orderDtoResponse = mapper.map(orderResponse, OrderDto.class);

		if (!body.getProductOrders().isEmpty()) {
			List<ProductOrderFormDto> listProductOrderForm = body.getProductOrders();
			List<ProductOrderDto> listProductOrder = new ArrayList<>();

			for(ProductOrderFormDto productOrderFormDto : listProductOrderForm) {
				mapper.map(productOrderFormDto, ProductOrderEntity.class);
				ProductOrderDto productOrderResponse = this.productOrderService.save(productOrderFormDto, orderResponse);
				order.setTotal(order.getTotal() + productOrderResponse.getTotal());
				orderDtoResponse.setTotal(order.getTotal());
				listProductOrder.add(productOrderResponse);
			}
			orderDtoResponse.setProductOrders(listProductOrder);
		}

		paymentPublisher.publishPayment(order);
        return orderDtoResponse;
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
