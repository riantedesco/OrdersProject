package com.compass.msorder.controller;

import com.compass.msorder.fixture.ProductFixture;
import com.compass.msorder.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.productController);
    }

    @Test
    public void saveProduct_WhenSendMethodPost_ExpectedStatus201() {
        when(this.productService.save(ProductFixture.getProductFormDto())).thenReturn(ProductFixture.getProductDto());

        given()
                .contentType("application/json")
                .body(ProductFixture.getProductFormDto())
                .when()
                .post("/v1/product")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void findProduct_WhenSendMethodGetById_ExpectedStatus200k() {
        when(this.productService.find(ProductFixture.getProductEntity().getId())).thenReturn(ProductFixture.getProductDto());

        given()
                .when()
                .get("/v1/product/{id}", ProductFixture.getProductEntity().getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void listProducts_WhenSendMethodGet_ExpectedStatus200() {
        when(this.productService.list()).thenReturn(List.of(ProductFixture.getProductDto()));

        given()
                .when()
                .get("/v1/product")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void updateProduct_WhenSendMethodUpdateById_ExpectedStatus200() {
        when(this.productService.update(ProductFixture.getProductEntity().getId(), ProductFixture.getProductFormDto())).thenReturn(ProductFixture.getProductDto());

        given()
                .contentType("application/json")
                .body(ProductFixture.getProductFormDto())
                .when()
                .put("/v1/product/{id}", ProductFixture.getProductEntity().getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deleteProduct_WhenSendMethodDeleteById_ExpectedStatus200() {
        doNothing().when(this.productService).delete(ProductFixture.getProductEntity().getId());

        given()
                .when()
                .delete("/v1/product/{id}", ProductFixture.getProductEntity().getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}