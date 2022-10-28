package com.compass.msorder.controller;

import com.compass.msorder.domain.ClientEntity;
import com.compass.msorder.domain.ProductEntity;
import com.compass.msorder.domain.dto.ClientDto;
import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.domain.dto.form.ClientFormDto;
import com.compass.msorder.domain.dto.form.ProductFormDto;
import com.compass.msorder.fixture.ClientFormDtoFixture;
import com.compass.msorder.fixture.ProductFormDtoFixture;
import com.compass.msorder.repository.ClientRepository;
import com.compass.msorder.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ClientRepository clientRepository;

    ClientEntity client;

    @BeforeAll
    public void setup() {
        this.client = new ClientEntity();
        client.setCpf("000.000.000-00");
        client.setName("Client test");
        client.setSex("Masculino");
        client.setBirthdate(LocalDate.parse("2002-03-30"));
        client.setEmail("test@email.com");
        client.setPhone("(00)00000-0000");
    }

    @Test
    public void createClientTest() {
        ClientFormDto clientFormDto = ClientFormDtoFixture.getDefault();

        HttpEntity<ClientFormDto> httpEntity = new HttpEntity<>(clientFormDto);

        ResponseEntity<ClientDto> response = this.testRestTemplate
                .exchange("/v1/client", HttpMethod.POST, httpEntity, ClientDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getName(), "Client default");
    }

    @Test
    public void listClientTest() {
        ResponseEntity<ClientDto[]> response = this.testRestTemplate
                .exchange("/v1/client", HttpMethod.GET, null, ClientDto[].class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void findClientTest() {
        ClientEntity client = this.clientRepository.save(this.client);

        ResponseEntity<ClientDto> response = this.testRestTemplate
                .exchange("/v1/client/" + client.getId(), HttpMethod.GET, null, ClientDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getName(), "Client test");
    }

    @Test
    public void updateClientTest() {
        ClientEntity client = this.clientRepository.save(this.client);

        ClientFormDto clientFormDto = ClientFormDtoFixture.getDefault();
        clientFormDto.setName("Client updated");

        HttpEntity<ClientFormDto> httpEntity = new HttpEntity<>(clientFormDto);

        ResponseEntity<ClientDto> response = this.testRestTemplate
                .exchange("/v1/client/" + client.getId(), HttpMethod.PUT, httpEntity, ClientDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getName(), "Client updated");
    }

    @Test
    public void deleteClientTest() {
        ClientEntity client = this.clientRepository.save(this.client);

        ResponseEntity<Void> response = this.testRestTemplate
                .exchange("/v1/client/" + client.getId(), HttpMethod.DELETE, null, Void.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}