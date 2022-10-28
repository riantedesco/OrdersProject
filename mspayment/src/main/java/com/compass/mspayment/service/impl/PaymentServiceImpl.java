package com.compass.mspayment.service.impl;

import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.exception.NotFoundAttributeException;
import com.compass.mspayment.listener.order.dto.OrderListenerDto;
import com.compass.mspayment.publisher.order.OrderPublisher;
import com.compass.mspayment.repository.PaymentRepository;
import com.compass.mspayment.service.PaymentService;
import com.compass.mspayment.util.constants.StatusOrderOption;
import com.compass.mspayment.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private OrderPublisher orderPublisher;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private Validation validation;

	@Override
	public void save(OrderListenerDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		PaymentEntity payment = mapper.map(body, PaymentEntity.class);

		if (body.getPrice() >= 1000.00) {
			payment.setStatus(StatusOrderOption.PAYMENT_UNAUTHORIZED);
		} else {
			payment.setStatus(StatusOrderOption.PAYMENT_CONFIRMED);
		}

		validation.validatePayment(payment);
		this.paymentRepository.save(payment);
		orderPublisher.publishOrder(payment);
	}

	@Override
	public PaymentDto findByIdOrderAndCpfClient(Long idOrder, String cpfClient) {
		Optional<PaymentEntity> payment = this.paymentRepository.findByIdOrderAndCpfClient(idOrder, cpfClient);
		if (!payment.isPresent()) {
			throw new NotFoundAttributeException("Payment not found");
		}
		return mapper.map(payment.get(), PaymentDto.class);
	}

}
