package com.compass.mspayment.controller;

import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.repository.PaymentRepository;
import com.compass.mspayment.util.constants.StatusOrderOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PaymentRepository paymentRepository;

    PaymentEntity payment;

    @BeforeAll
    public void setup() {
        this.payment = new PaymentEntity();
        payment.setIdOrder(1L);
        payment.setCpfClient("000.000.000-00");
        payment.setPrice(999.00);
        payment.setStatus(StatusOrderOption.PAYMENT_CONFIRMED);

    }

    @Test
    public void findPaymentTest() {
        PaymentEntity payment = this.paymentRepository.save(this.payment);

        ResponseEntity<PaymentDto> response = this.testRestTemplate
                .exchange("/v1/payment/find?idOrder=" + payment.getId() +  "&cpfClient=" + payment.getCpfClient(),
                        HttpMethod.GET, null, PaymentDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getIdOrder(), 1L);
        Assertions.assertEquals(response.getBody().getCpfClient(), "000.000.000-00");
    }
}