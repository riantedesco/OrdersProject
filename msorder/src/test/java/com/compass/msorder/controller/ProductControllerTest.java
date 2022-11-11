package com.compass.msorder.controller;

import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.fixture.ProductFixture;
import com.compass.msorder.service.ProductService;
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
public class ProductControllerTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        webAppContextSetup(webApplicationContext);
    }

    @Test
    public void saveProduct_WhenSendMethodPost_ExpectedStatus201() {
        given()
                .contentType("application/json")
                .body(ProductFixture.getProductFormDto())
                .when()
                .post("/v1/product")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void saveProduct_WhenSendMethodPost_ExpectedStatus400() {
        given()
                .contentType("application/json")
                .body(ProductFixture.getProductFormWithInvalidPrice())
                .when()
                .post("/v1/product")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void findProduct_WhenSendMethodGetById_ExpectedStatus200() {
        ProductDto productSaved = productService.save(ProductFixture.getProductFormDto());

        given()
                .when()
                .get("/v1/product/{id}", productSaved.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findClient_WhenSendMethodGetById_ExpectedStatus404() {
        given()
                .when()
                .get("/v1/product/{id}", 5000L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void listProducts_WhenSendMethodGet_ExpectedStatus200() {
        given()
                .when()
                .get("/v1/product")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void updateProduct_WhenSendMethodUpdateById_ExpectedStatus200() {
        ProductDto productSaved = productService.save(ProductFixture.getProductFormDto());

        given()
                .contentType("application/json")
                .body(ProductFixture.getProductFormDto())
                .when()
                .put("/v1/product/{id}", productSaved.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void updateProduct_WhenSendMethodUpdateById_ExpectedStatus400() {
        ProductDto productSaved = productService.save(ProductFixture.getProductFormDto());

        given()
                .contentType("application/json")
                .body(ProductFixture.getProductFormWithInvalidPrice())
                .when()
                .put("/v1/product/{id}", productSaved.getId())
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void updateProduct_WhenSendMethodUpdateById_ExpectedStatus404() {
        given()
                .contentType("application/json")
                .body(ProductFixture.getProductFormDto())
                .when()
                .put("/v1/product/{id}", 5000L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deleteProduct_WhenSendMethodDeleteById_ExpectedStatus200() {
        ProductDto productSaved = productService.save(ProductFixture.getProductFormDto());

        given()
                .when()
                .delete("/v1/product/{id}", productSaved.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deleteProduct_WhenSendMethodDeleteById_ExpectedStatus404() {
        given()
                .when()
                .delete("/v1/product/{id}", 5000L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}