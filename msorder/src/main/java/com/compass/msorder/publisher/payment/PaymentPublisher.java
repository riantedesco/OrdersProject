package com.compass.msorder.publisher.payment;

import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.publisher.payment.dto.PaymentPublisherDto;
import com.compass.msorder.util.constants.RabbitMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishPayment(OrderEntity order) {
        PaymentPublisherDto paymentPublisherDto = new PaymentPublisherDto(order.getId(), order.getCustomer().getCpf(), order.getTotal(), order.getStatus());
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.ORDER_NOTIFICATION_ROUTINGKEY_NAME, paymentPublisherDto);
        log.info("PaymentPublisher.publish - {}", order);
    }
}
