package com.compass.msorder.controller;

import com.compass.msorder.domain.dto.ClientDto;
import com.compass.msorder.fixture.ClientFixture;
import com.compass.msorder.service.ClientService;
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
public class ClientControllerTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        webAppContextSetup(webApplicationContext);
    }

    @Test
    public void saveClient_WhenSendMethodPost_ExpectedStatus201() {
        given()
                .contentType("application/json")
                .body(ClientFixture.getClientFormDto())
                .when()
                .post("/v1/client")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void saveClient_WhenSendMethodPost_ExpectedStatus400() {
        given()
                .contentType("application/json")
                .body(ClientFixture.getClientFormDtoWithInvalidAttribute())
                .when()
                .post("/v1/client")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void findClient_WhenSendMethodGetById_ExpectedStatus200() {
        ClientDto clientSaved = clientService.save(ClientFixture.getClientFormDto());

        given()
                .when()
                .get("/v1/client/{id}", clientSaved.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findClient_WhenSendMethodGetById_ExpectedStatus404() {
        given()
                .when()
                .get("/v1/client/{id}", 5000L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void listClients_WhenSendMethodGet_ExpectedStatus200() {
        given()
                .when()
                .get("/v1/client")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void updateClient_WhenSendMethodUpdateById_ExpectedStatus200() {
        ClientDto clientSaved = clientService.save(ClientFixture.getClientFormDto());

        given()
                .contentType("application/json")
                .body(ClientFixture.getClientFormDto())
                .when()
                .put("/v1/client/{id}", clientSaved.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void updateClient_WhenSendMethodUpdateById_ExpectedStatus400() {
        ClientDto clientSaved = clientService.save(ClientFixture.getClientFormDto());

        given()
                .contentType("application/json")
                .body(ClientFixture.getClientFormDtoWithInvalidAttribute())
                .when()
                .put("/v1/client/{id}", clientSaved.getId())
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void updateClient_WhenSendMethodUpdateById_ExpectedStatus404() {
        given()
                .contentType("application/json")
                .body(ClientFixture.getClientFormDto())
                .when()
                .put("/v1/client/{id}", 5000L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deleteClient_WhenSendMethodDeleteById_ExpectedStatus200() {
        ClientDto clientSaved = clientService.save(ClientFixture.getClientFormDto());

        given()
                .when()
                .delete("/v1/client/{id}", clientSaved.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deleteClient_WhenSendMethodDeleteById_ExpectedStatus404() {
        given()
                .when()
                .delete("/v1/client/{id}", 5000L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}