package com.compass.msorder.listener.payment;

import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.listener.payment.dto.PaymentListenerDto;
import com.compass.msorder.repository.OrderRepository;
import com.compass.msorder.util.constants.RabbitMQConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentListener {

    @Autowired
    private OrderRepository orderRepository;

    @RabbitListener(queues = RabbitMQConstants.PAYMENT_NOTIFICATION_QUEUE_NAME)
    public void consumeOrderListener(PaymentListenerDto paymentListenerDto) {
        Optional<OrderEntity> order = this.orderRepository.findById(paymentListenerDto.getIdOrder());
        if (!order.isPresent()) {
            throw new NotFoundAttributeException("Order not found");
        }
        order.get().setStatus(paymentListenerDto.getStatus());
        this.orderRepository.save(order.get());
    }
}
