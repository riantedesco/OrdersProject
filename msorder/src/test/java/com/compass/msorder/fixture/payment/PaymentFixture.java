package com.compass.msorder.fixture.payment;

import com.compass.msorder.fixture.ClientFixture;
import com.compass.msorder.fixture.OrderFixture;
import com.compass.msorder.listener.payment.dto.PaymentListenerDto;
import com.compass.msorder.publisher.payment.dto.PaymentPublisherDto;
import com.compass.msorder.util.constants.StatusOrderOption;

public class PaymentFixture {

    public static PaymentPublisherDto getPaymentPublisherDto() {
        return PaymentPublisherDto.builder()
                .idOrder(OrderFixture.getOrderEntity().getId())
                .cpfClient(ClientFixture.getClientEntity().getCpf())
                .price(0.00)
                .status(StatusOrderOption.ORDER_CREATED)
                .build();
    }

    public static PaymentListenerDto getPaymentListenerDto() {
        return PaymentListenerDto.builder()
                .idPayment(1L)
                .idOrder(OrderFixture.getOrderEntity().getId())
                .status(StatusOrderOption.PAYMENT_CONFIRMED)
                .build();
    }
}
