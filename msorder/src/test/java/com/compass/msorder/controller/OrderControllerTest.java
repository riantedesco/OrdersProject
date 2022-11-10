package com.compass.msorder.controller;

import com.compass.msorder.fixture.OrderFixture;
import com.compass.msorder.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.orderController);
    }

    @Test
    public void saveOrder_WhenSendMethodPost_ExpectedStatus201() {
        when(this.orderService.save(OrderFixture.getOrderFormDto())).thenReturn(OrderFixture.getOrderDto());

        given()
                .contentType("application/json")
                .body(OrderFixture.getOrderFormDto())
                .when()
                .post("/v1/order")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void findOrder_WhenSendMethodGetByIdAndNumberAndCpfClient_ExpectedStatus200() {
        when(this.orderService.findByIdNumberAndCpfClient(OrderFixture.getOrderDto().getId(), OrderFixture.getOrderEntity().getNumber(), OrderFixture.getOrderEntity().getClient().getCpf())).thenReturn(OrderFixture.getOrderDto());

        given()
                .param("id", OrderFixture.getOrderEntity().getId())
                .param("number", OrderFixture.getOrderEntity().getNumber())
                .param("cpfClient", OrderFixture.getOrderEntity().getClient().getCpf())
                .when()
                .get("/v1/order/find").then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void updateOrder_WhenSendMethodUpdateById_ExpectedStatus200() {
        when(this.orderService.update(OrderFixture.getOrderDto().getId(), OrderFixture.getOrderUpdateFormDto())).thenReturn(OrderFixture.getOrderUpdateDto());

        given()
                .contentType("application/json")
                .body(OrderFixture.getOrderUpdateFormDto())
                .when()
                .put("/v1/order/{id}", OrderFixture.getOrderEntity().getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}