package com.compass.msorder.service;

import com.compass.msorder.domain.CustomerEntity;
import com.compass.msorder.domain.dto.CustomerDto;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.CustomerFixture;
import com.compass.msorder.repository.CustomerRepository;
import com.compass.msorder.service.impl.CustomerServiceImpl;
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
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private ModelMapper mapper;

    @Mock
    private Validation validation;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_WhenSendSave_ExpectedCustomer ()  {
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(CustomerFixture.getCustomerEntity());
        CustomerDto response = customerService.save(CustomerFixture.getCustomerFormDto());

        verify(customerRepository, times(1)).save(any(CustomerEntity.class));
        assertEquals(response.getId(), CustomerFixture.getCustomerEntity().getId());
        assertNotNull(response);
    }

    @Test
    void list_WhenSendList_ExpectedCustomers ()  {
        when(customerRepository.findAll()).thenReturn(List.of(CustomerFixture.getCustomerEntity()));
        List<CustomerDto> response = customerService.list();

        assertFalse(response.isEmpty());
    }

    @Test
    void find_WhenSendFindWithExistingId_ExpectedCustomer ()  {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerFixture.getCustomerEntity()));
        CustomerDto response = customerService.find(CustomerFixture.getCustomerEntity().getId());

        assertNotNull(response);
    }

    @Test
    void find_WhenSendFindWithNonExistingId_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> customerService.find(1L));

        assertNotNull(response);
        assertEquals("Customer not found", response.getMessage());
    }

    @Test
    void update_WhenSendUpdateWithExistingId_ExpectedCustomer ()  {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerFixture.getCustomerEntity()));
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(CustomerFixture.getCustomerEntity());
        CustomerDto response = customerService.update(CustomerFixture.getCustomerEntity().getId(), CustomerFixture.getCustomerFormDto());

        assertEquals(CustomerFixture.getCustomerDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void update_WhenSendUpdateWithNonExistingId_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> customerService.update(1L, CustomerFixture.getCustomerFormDto()));

        assertNotNull(response);
        assertEquals("Customer not found", response.getMessage());
    }

    @Test
    void delete_WhenSendDeleteWithExistingId_ExpectedOk ()  {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerFixture.getCustomerEntity()));
        doNothing().when(customerRepository).deleteById(anyLong());
        customerService.delete(CustomerFixture.getCustomerEntity().getId());

        verify(customerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void delete_WhenSendDeleteWithNonExistingId_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> customerService.delete(1L));

        assertNotNull(response);
        assertEquals("Customer not found", response.getMessage());
    }

}
