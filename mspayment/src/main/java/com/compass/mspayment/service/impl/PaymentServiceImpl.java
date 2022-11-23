package com.compass.mspayment.service.impl;

import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.domain.dto.form.PaymentFormDto;
import com.compass.mspayment.exception.NotFoundAttributeException;
import com.compass.mspayment.publisher.order.OrderPublisher;
import com.compass.mspayment.repository.PaymentRepository;
import com.compass.mspayment.service.PaymentService;
import com.compass.mspayment.util.constants.StatusOrderOption;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private OrderPublisher orderPublisher;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void save(PaymentFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		PaymentEntity payment = mapper.map(body, PaymentEntity.class);

		if (body.getTotalOrder() >= 1000.00) {
			payment.setStatusOrder(StatusOrderOption.PAYMENT_UNAUTHORIZED);
		} else {
			payment.setStatusOrder(StatusOrderOption.PAYMENT_CONFIRMED);
		}

		this.paymentRepository.save(payment);
		orderPublisher.publishOrder(payment);
	}

	@Override
	public PaymentDto findByIdOrderAndCpfCustomer(Long idOrder, String cpfCustomer) {
		Optional<PaymentEntity> payment = this.paymentRepository.findByIdOrderAndCpfCustomer(idOrder, cpfCustomer);
		if (!payment.isPresent()) {
			throw new NotFoundAttributeException("Payment not found");
		}
		return mapper.map(payment.get(), PaymentDto.class);
	}

}
