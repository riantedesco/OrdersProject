package com.compass.mspayment.controller;

import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.fixture.PaymentFixture;
import com.compass.mspayment.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
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
    public void find_WhenSendFindByIdAndCpfClientWithExistingIdOrderAndCpfClient_ExpectedStatus200() {
        PaymentEntity paymentSaved = paymentRepository.save(PaymentFixture.getPaymentEntityWithAuthorizedPrice());

        given()
                .param("idOrder", paymentSaved.getId())
                .param("cpfCustomer", paymentSaved.getCpfCustomer())
                .when()
                .get("/v1/payments/find").then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void find_WhenSendFindByIdAndCpfClientWithNonExistingIdOrderOrCpfClient_ExpectedStatus404() {
        given()
                .param("idOrder", 5000L)
                .param("cpfCustomer", PaymentFixture.getPaymentEntity().getCpfCustomer())
                .when()
                .get("/v1/payments/find").then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}