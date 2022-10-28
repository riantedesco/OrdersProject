package com.compass.mspayment.service;

import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.exception.NotFoundAttributeException;
import com.compass.mspayment.fixture.PaymentFormDtoFixture;
import com.compass.mspayment.util.constants.StatusOrderOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    void shouldSaveWithPaymentConfirmed ()  {
        this.paymentService.save(PaymentFormDtoFixture.getWithAuthorizedPrice());
        PaymentDto payment = this.paymentService.findByIdOrderAndCpfClient(PaymentFormDtoFixture.getWithAuthorizedPrice().getIdOrder(),
                PaymentFormDtoFixture.getWithAuthorizedPrice().getCpfClient());
        Assertions.assertEquals(payment.getStatus(), StatusOrderOption.PAYMENT_CONFIRMED);
    }

    @Test
    void shouldSaveWithPaymentUnauthorized ()  {
        this.paymentService.save(PaymentFormDtoFixture.getWithUnauthorizedPrice());
        PaymentDto payment = this.paymentService.findByIdOrderAndCpfClient(PaymentFormDtoFixture.getWithUnauthorizedPrice().getIdOrder(),
                PaymentFormDtoFixture.getWithUnauthorizedPrice().getCpfClient());
        Assertions.assertEquals(payment.getStatus(), StatusOrderOption.PAYMENT_UNAUTHORIZED);
    }

    @Test
    void shouldNotFind ()  {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.paymentService.findByIdOrderAndCpfClient(5000L, "999.999.999-99");
        });
        Assertions.assertTrue(exception.getMessage().contains("Payment not found"));
    }
}
