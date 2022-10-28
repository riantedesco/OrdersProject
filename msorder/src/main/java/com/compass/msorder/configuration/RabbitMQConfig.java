package com.compass.msorder.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.compass.msorder.util.constants.RabbitMQConstants.*;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queuePaymentPublisher() {
        return new Queue(ORDER_NOTIFICATION_QUEUE_NAME, true);
    }

    @Bean
    public Queue queuePaymentListener() {
        return new Queue(PAYMENT_NOTIFICATION_QUEUE_NAME, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding bindingPaymentPublisher() {
        return BindingBuilder.bind(queuePaymentPublisher()).to(exchange()).with(ORDER_NOTIFICATION_ROUTINGKEY_NAME);
    }

    @Bean
    Binding bindingPaymentListener() {
        return BindingBuilder.bind(queuePaymentListener()).to(exchange()).with(PAYMENT_NOTIFICATION_ROUTINGKEY_NAME);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
