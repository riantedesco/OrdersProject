package com.compass.msorder.service;

import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.ClientFormDtoFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    void saveClient_WhenSendSaveWithInvalidCpf_ExpectedInvalidAttributeException ()  {
        Exception exception = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.clientService.save(ClientFormDtoFixture.getWithInvalidCpf());
        });
        Assertions.assertTrue(exception.getMessage().contains("Cpf must  11 characters"));
    }

    @Test
    void saveClient_WhenSendSaveWithInvalidName_ExpectedInvalidAttributeException ()  {
        Exception exception = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.clientService.save(ClientFormDtoFixture.getWithInvalidName());
        });
        Assertions.assertTrue(exception.getMessage().contains("Name must contain at least 3 characters"));
    }

    @Test
    void saveClient_WhenSendSaveWithInvalidSex_ExpectedInvalidAttributeException ()  {
        Exception exception = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.clientService.save(ClientFormDtoFixture.getWithInvalidSex());
        });
        Assertions.assertTrue(exception.getMessage().contains("Invalid sex"));
    }

    @Test
    void saveClient_WhenSendSaveWithInvalidEmail_ExpectedInvalidAttributeException ()  {
        Exception exception = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.clientService.save(ClientFormDtoFixture.getWithInvalidEmail());
        });
        Assertions.assertTrue(exception.getMessage().contains("Invalid email"));
    }

    @Test
    void saveClient_WhenSendSaveWithInvalidPhone_ExpectedInvalidAttributeException ()  {
        Exception exception = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.clientService.save(ClientFormDtoFixture.getWithInvalidPhone());
        });
        Assertions.assertTrue(exception.getMessage().contains("Phone must contain 11 characters"));
    }

    @Test
    void findClient_WhenSendFindByIdWithNotFoundClient_ExpectedNotFoundAttributeException ()  {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.clientService.find(5000L);
        });
        Assertions.assertTrue(exception.getMessage().contains("Client not found"));
    }

    @Test
    void updateClient_WhenSendUpdateByIdWithNotFoundClient_ExpectedNotFoundAttributeException () {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.clientService.update(5000L, ClientFormDtoFixture.getDefault());
        });
        Assertions.assertTrue(exception.getMessage().contains("Client not found"));
    }

    @Test
    void deleteClient_WhenSendDeleteByIdWithNotFoundClient_ExpectedNotFoundAttributeException () {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.clientService.delete(5000L);
        });
        Assertions.assertTrue(exception.getMessage().contains("Client not found"));
    }
}
