package com.compass.msorder.controller;

import com.compass.msorder.fixture.ClientFixture;
import com.compass.msorder.service.ClientService;
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

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private ClientController clientController;

    @MockBean
    private ClientService clientService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.clientController);
    }

    @Test
    public void saveClient_WhenSendMethodPost_ExpectedStatus201() {
        when(this.clientService.save(ClientFixture.getClientFormDto())).thenReturn(ClientFixture.getClientDto());

        given()
                .contentType("application/json")
                .body(ClientFixture.getClientFormDto())
                .when()
                .post("/v1/client")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void findClient_WhenSendMethodGetById_ExpectedStatus200() {
        when(this.clientService.find(ClientFixture.getClientEntity().getId())).thenReturn(ClientFixture.getClientDto());

        given()
                .when()
                .get("/v1/client/{id}", ClientFixture.getClientEntity().getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void listClients_WhenSendMethodGet_ExpectedStatus200() {
        when(this.clientService.list()).thenReturn(List.of(ClientFixture.getClientDto()));

        given()
                .when()
                .get("/v1/client")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void updateClient_WhenSendMethodUpdateById_ExpectedStatus200() {
        when(this.clientService.update(ClientFixture.getClientEntity().getId(), ClientFixture.getClientFormDto())).thenReturn(ClientFixture.getClientDto());

        given()
                .contentType("application/json")
                .body(ClientFixture.getClientFormDto())
                .when()
                .put("/v1/client/{id}", ClientFixture.getClientEntity().getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deleteProduct_WhenSendMethodDeleteById_ExpectedStatus200() {
        doNothing().when(this.clientService).delete(ClientFixture.getClientEntity().getId());

        given()
                .when()
                .delete("/v1/client/{id}", ClientFixture.getClientEntity().getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}