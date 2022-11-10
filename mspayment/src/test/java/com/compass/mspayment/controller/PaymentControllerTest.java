package com.compass.mspayment.controller;

import com.compass.mspayment.fixture.PaymentFixture;
import com.compass.mspayment.service.PaymentService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private PaymentController paymentController;

    @MockBean
    private PaymentService paymentService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.paymentController);
    }

    @Test
    public void findPayment_WhenSendMethodGetWithIdOrderAndCpfClientValid_ExpectedStatus200() {
        when(this.paymentService.findByIdOrderAndCpfClient(PaymentFixture.getPaymentEntity().getIdOrder(), PaymentFixture.getPaymentEntity().getCpfClient())).thenReturn(PaymentFixture.getPaymentDto());

        given().accept(ContentType.JSON)
                .param("idOrder", PaymentFixture.getPaymentEntity().getIdOrder())
                .param("cpfClient", PaymentFixture.getPaymentEntity().getCpfClient())
                .when()
                .get("/v1/payment/find").then()
                .statusCode(HttpStatus.OK.value());
    }
}