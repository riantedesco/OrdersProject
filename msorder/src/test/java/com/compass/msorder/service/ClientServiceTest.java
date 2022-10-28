package com.compass.msorder.service;

import com.compass.msorder.domain.dto.ClientDto;
import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.ClientFormDtoFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    void shouldNotSave ()  {
        Exception exceptionCpf = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.clientService.save(ClientFormDtoFixture.getWithInvalidCpf());
        });
        Exception exceptionName = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.clientService.save(ClientFormDtoFixture.getWithInvalidName());
        });
        Exception exceptionSex = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.clientService.save(ClientFormDtoFixture.getWithInvalidSex());
        });
        Exception exceptionEmail = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.clientService.save(ClientFormDtoFixture.getWithInvalidEmail());
        });
        Exception exceptionPhone = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.clientService.save(ClientFormDtoFixture.getWithInvalidPhone());
        });

        Assertions.assertTrue(exceptionCpf.getMessage().contains("Cpf must  11 characters"));
        Assertions.assertTrue(exceptionName.getMessage().contains("Name must contain at least 3 characters"));
        Assertions.assertTrue(exceptionSex.getMessage().contains("Invalid sex"));
        Assertions.assertTrue(exceptionEmail.getMessage().contains("Invalid email"));
        Assertions.assertTrue(exceptionPhone.getMessage().contains("Phone must contain 11 characters"));
    }

    @Test
    void shouldNotFind ()  {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.clientService.find(5000L);
        });
        Assertions.assertTrue(exception.getMessage().contains("Client not found"));
    }

    @Test
    void shouldNotUpdate () {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.clientService.update(5000L, ClientFormDtoFixture.getDefault());
        });
        Assertions.assertTrue(exception.getMessage().contains("Client not found"));
    }

    @Test
    void shouldNotDelete () {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.clientService.delete(5000L);
        });
        Assertions.assertTrue(exception.getMessage().contains("Client not found"));
    }
}
