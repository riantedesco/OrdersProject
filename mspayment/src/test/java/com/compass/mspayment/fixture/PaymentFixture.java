package com.compass.mspayment.fixture;

import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.domain.dto.form.PaymentFormDto;
import com.compass.mspayment.util.constants.StatusOrderOption;

public class PaymentFixture {

    public static PaymentEntity getPaymentEntity() {
        return PaymentEntity.builder()
                .id(1L)
                .idOrder(1L)
                .cpfCustomer("000.000.000-00")
                .totalOrder(900.00)
                .statusOrder(StatusOrderOption.ORDER_CREATED)
                .build();
    }

    public static PaymentFormDto getPaymentFormDtoWithAuthorizedPrice() {
        return PaymentFormDto.builder()
                .idOrder(1L)
                .cpfCustomer("000.000.000-00")
                .totalOrder(900.00)
                .build();
    }

    public static PaymentFormDto getPaymentFormDtoWithUnauthorizedPrice() {
        return PaymentFormDto.builder()
                .idOrder(1L)
                .cpfCustomer("000.000.000-00")
                .totalOrder(1100.00)
                .build();
    }

    public static PaymentEntity getPaymentEntityWithAuthorizedPrice() {
        return PaymentEntity.builder()
                .id(1L)
                .idOrder(1L)
                .cpfCustomer("000.000.000-00")
                .totalOrder(900.00)
                .statusOrder(StatusOrderOption.PAYMENT_CONFIRMED)
                .build();
    }

}
