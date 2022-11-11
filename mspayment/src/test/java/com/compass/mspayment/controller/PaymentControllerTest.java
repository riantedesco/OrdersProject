package com.compass.mspayment.controller;

import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.domain.dto.form.PaymentFormDto;
import com.compass.mspayment.fixture.PaymentFixture;
import com.compass.mspayment.repository.PaymentRepository;
import com.compass.mspayment.service.PaymentService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PaymentControllerTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        webAppContextSetup(webApplicationContext);
    }

    @Test
    public void findPayment_WhenSendMethodGetWithIdOrderAndCpfClientValid_ExpectedStatus200() {
        PaymentEntity paymentSaved = paymentRepository.save(PaymentFixture.getPaymentEntityWithAuthorizedPrice());

        given()
                .param("idOrder", paymentSaved.getId())
                .param("cpfClient", paymentSaved.getCpfClient())
                .when()
                .get("/v1/payment/find").then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findPayment_WhenSendMethodGetWithIdOrderAndCpfClientValid_ExpectedStatus404() {
        given()
                .param("idOrder", 5000L)
                .param("cpfClient", PaymentFixture.getPaymentEntity().getCpfClient())
                .when()
                .get("/v1/payment/find").then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}