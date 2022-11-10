package com.compass.msorder.service;

import com.compass.msorder.domain.ClientEntity;
import com.compass.msorder.domain.dto.ClientDto;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.ClientFixture;
import com.compass.msorder.repository.ClientRepository;
import com.compass.msorder.service.impl.ClientServiceImpl;
import com.compass.msorder.util.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Spy
    private ModelMapper mapper;

    @Mock
    private Validation validation;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveClient_WhenSendSaveClientValid_ExpectedClient ()  {
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(ClientFixture.getClientEntity());
        ClientDto response = clientService.save(ClientFixture.getClientFormDto());

        verify(clientRepository, times(1)).save(any(ClientEntity.class));
        assertEquals(response.getId(), ClientFixture.getClientEntity().getId());
        assertNotNull(response);
    }

    @Test
    void listClient_WhenSendListClients_ExpectedClients ()  {
        when(clientRepository.findAll()).thenReturn(List.of(ClientFixture.getClientEntity()));
        List<ClientDto> response = clientService.list();

        assertFalse(response.isEmpty());
    }

    @Test
    void findClient_WhenSendFindClientWithIdValid_ExpectedClient ()  {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(ClientFixture.getClientEntity()));
        ClientDto response = clientService.find(ClientFixture.getClientEntity().getId());

        assertNotNull(response);
    }

    @Test
    void findClient_WhenSendFindClientWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> clientService.find(1L));

        assertNotNull(response);
        assertEquals("Client not found", response.getMessage());
    }

    @Test
    void updateClient_WhenSendUpdateClientWithIdValid_ExpectedClient ()  {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(ClientFixture.getClientEntity()));
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(ClientFixture.getClientEntity());
        ClientDto response = clientService.update(ClientFixture.getClientEntity().getId(), ClientFixture.getClientFormDto());

        assertEquals(ClientFixture.getClientDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void updateClient_WhenSendUpdateClientWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> clientService.update(1L, ClientFixture.getClientFormDto()));

        assertNotNull(response);
        assertEquals("Client not found", response.getMessage());
    }

    @Test
    void deleteClient_WhenSendDeleteClientWithIdValid_ExpectedOk ()  {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(ClientFixture.getClientEntity()));
        doNothing().when(clientRepository).deleteById(anyLong());
        clientService.delete(ClientFixture.getClientEntity().getId());

        verify(clientRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteClient_WhenSendDeleteClientWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> clientService.delete(1L));

        assertNotNull(response);
        assertEquals("Client not found", response.getMessage());
    }

}
