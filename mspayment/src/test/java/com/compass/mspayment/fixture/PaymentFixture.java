package com.compass.mspayment.fixture;

import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.domain.dto.form.PaymentFormDto;
import com.compass.mspayment.util.constants.StatusOrderOption;

public class PaymentFixture {

    public static PaymentEntity getPaymentEntity() {
        return PaymentEntity.builder()
                .id(1L)
                .idOrder(1L)
                .cpfClient("000.000.000-00")
                .price(900.00)
                .status(StatusOrderOption.ORDER_CREATED)
                .build();
    }

    public static PaymentFormDto getPaymentFormDtoWithAuthorizedPrice() {
        return PaymentFormDto.builder()
                .idOrder(1L)
                .cpfClient("000.000.000-00")
                .price(900.00)
                .build();
    }

    public static PaymentFormDto getPaymentFormDtoWithUnauthorizedPrice() {
        return PaymentFormDto.builder()
                .idOrder(1L)
                .cpfClient("000.000.000-00")
                .price(1100.00)
                .build();
    }

    public static PaymentEntity getPaymentEntityWithAuthorizedPrice() {
        return PaymentEntity.builder()
                .id(1L)
                .idOrder(1L)
                .cpfClient("000.000.000-00")
                .price(900.00)
                .status(StatusOrderOption.PAYMENT_CONFIRMED)
                .build();
    }

}
