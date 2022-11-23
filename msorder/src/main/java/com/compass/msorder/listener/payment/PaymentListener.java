package com.compass.msorder.listener.payment;

import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.listener.payment.dto.PaymentListenerDto;
import com.compass.msorder.repository.OrderRepository;
import com.compass.msorder.util.constants.RabbitMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class PaymentListener {

    @Autowired
    private OrderRepository orderRepository;

    @RabbitListener(queues = RabbitMQConstants.PAYMENT_NOTIFICATION_QUEUE_NAME)
    public void listenPayment(PaymentListenerDto paymentListenerDto) {
        log.info("PaymentListener.listen - {}", paymentListenerDto);
        Optional<OrderEntity> order = this.orderRepository.findById(paymentListenerDto.getIdOrder());
        order.get().setStatus(paymentListenerDto.getStatusOrder());
        this.orderRepository.save(order.get());
    }
}
