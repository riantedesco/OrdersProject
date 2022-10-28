package com.compass.mspayment.listener.order;

import com.compass.mspayment.listener.order.dto.OrderListenerDto;
import com.compass.mspayment.service.PaymentService;
import com.compass.mspayment.util.constants.RabbitMQConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @Autowired
    private PaymentService paymentService;

    @RabbitListener(queues = RabbitMQConstants.ORDER_NOTIFICATION_QUEUE_NAME)
    public void consumeOrderListener(OrderListenerDto orderListenerDto) {
        this.paymentService.save(orderListenerDto);
    }
}
