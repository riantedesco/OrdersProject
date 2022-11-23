package com.compass.msorder.controller;

import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.fixture.OrderFixture;
import com.compass.msorder.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.webAppContextSetup;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class OrderControllerTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        webAppContextSetup(webApplicationContext);
    }

    @Test
    public void save_WhenSendSaveWithValidBody_ExpectedStatus201() {
        given()
                .contentType("application/json")
                .body(OrderFixture.getOrderFormDto())
                .when()
                .post("/v1/orders")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void save_WhenSendSaveWithInvalidBody_ExpectedStatus400() {
        given()
                .contentType("application/json")
                .body(OrderFixture.getOrderFormDtoWithInvalidAttribute())
                .when()
                .post("/v1/orders")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void findByIdNumberAndCpfClient_WhenSendFindByIdNumberAndCpfClientWithExistingIdNumberAndCpfClient_ExpectedStatus200() {
        OrderDto orderSaved = orderService.save(OrderFixture.getOrderFormDto());

        given()
                .when()
                .get("/v1/orders/id={id}&number={number}&cpfClient={cpfClient}", orderSaved.getId(), orderSaved.getNumber(), orderSaved.getCustomer().getCpf()).then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findByIdNumberAndCpfClient_WhenSendFindByIdNumberAndCpfClientWithNonExistingIdNumberOrCpfClient_ExpectedStatus404() {
        given()
                .when()
                .get("/v1/orders/id={id}&number={number}&cpfClient={cpfClient}", 5000L, OrderFixture.getOrderEntity().getNumber(), OrderFixture.getOrderEntity().getCustomer().getCpf()).then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void update_WhenSendUpdateWithExistingIdAndValidBody_ExpectedStatus200() {
        OrderDto orderSaved = orderService.save(OrderFixture.getOrderFormDto());

        given()
                .contentType("application/json")
                .body(OrderFixture.getOrderUpdateFormDto())
                .when()
                .put("/v1/orders/{id}", orderSaved.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void update_WhenSendUpdateWithInvalidBody_ExpectedStatus400() {
        OrderDto orderSaved = orderService.save(OrderFixture.getOrderFormDto());

        given()
                .contentType("application/json")
                .body(OrderFixture.getOrderFormDtoWithInvalidCustomer())
                .when()
                .put("/v1/orders/{id}", orderSaved.getId())
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void update_WhenSendUpdateWithNonExistingId_ExpectedStatus404() {
        given()
                .contentType("application/json")
                .body(OrderFixture.getOrderFormDto())
                .when()
                .put("/v1/orders/{id}", 5000L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}