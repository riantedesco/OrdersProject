package com.compass.msorder.service.impl;

import com.compass.msorder.domain.CustomerEntity;
import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.domain.ProductOrderEntity;
import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.OrderUpdateDto;
import com.compass.msorder.domain.dto.ProductOrderDto;
import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.domain.dto.form.OrderUpdateFormDto;
import com.compass.msorder.domain.dto.form.ProductOrderFormDto;
import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.publisher.payment.PaymentPublisher;
import com.compass.msorder.repository.CustomerRepository;
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
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public OrderDto save(OrderFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		OrderEntity order = mapper.map(body, OrderEntity.class);
		order.setId(null);

		Optional<CustomerEntity> customer = this.customerRepository.findById(body.getIdCustomer());
		if(!customer.isPresent()) {
			throw new InvalidAttributeException("Customer not found");
		}
		order.setCustomer(customer.get());

		order.setStatus(StatusOrderOption.ORDER_CREATED);
		validation.validateSaveOrder(order);
		OrderEntity response = this.orderRepository.save(order);
		OrderDto orderDtoResponse = mapper.map(response, OrderDto.class);

		if (!body.getProductOrders().isEmpty()) {
			List<ProductOrderFormDto> listProductOrderForm = body.getProductOrders();
			List<ProductOrderDto> listProductOrder = new ArrayList<>();
			for(ProductOrderFormDto productOrderFormDto : listProductOrderForm) {
				mapper.map(productOrderFormDto, ProductOrderEntity.class);
				ProductOrderDto productOrderResponse = this.productOrderService.save(productOrderFormDto, response);
				order.setTotal(order.getTotal() + productOrderResponse.getTotal());
				orderDtoResponse.setTotal(order.getTotal());
				listProductOrder.add(productOrderResponse);
			}
			orderDtoResponse.setProductOrders(listProductOrder);
		}

		this.orderRepository.save(order);
		paymentPublisher.publishPayment(order);
        return orderDtoResponse;
	}

	@Override
	public OrderDto findByIdNumberAndCpfClient(Long id, Long number, String cpfClient) {
		Optional<OrderEntity> order = this.orderRepository.findByIdNumberAndCpfCustomer(id, number, cpfClient);
		if (!order.isPresent()) {
			throw new NotFoundAttributeException("Order not found");
		}
		return mapper.map(order.get(), OrderDto.class);
	}

	@Override
	public OrderUpdateDto update(Long id, OrderUpdateFormDto body) {
		Optional<OrderEntity> order = this.orderRepository.findById(id);
		if (!order.isPresent()) {
			throw new NotFoundAttributeException("Order not found");
		}
		order.get().setNumber(body.getNumber());

		Optional<CustomerEntity> customer = this.customerRepository.findById(body.getIdCustomer());
		if (!customer.isPresent()) {
			throw new InvalidAttributeException("Customer not found");
		}
		order.get().setCustomer(customer.get());

		validation.validateUpdateOrder(order.get());
		OrderEntity response = this.orderRepository.save(order.get());
		return mapper.map(response, OrderUpdateDto.class);
	}

}
