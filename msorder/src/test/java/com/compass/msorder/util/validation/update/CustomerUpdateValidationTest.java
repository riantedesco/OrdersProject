package com.compass.msorder.util.validation.update;

import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.fixture.CustomerFixture;
import com.compass.msorder.util.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class CustomerUpdateValidationTest {

    @InjectMocks
    private CustomerUpdateValidation customerUpdateValidation;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateCpf_WhenSendInvalidCpf_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () ->
                customerUpdateValidation.validateCpf(CustomerFixture.getCustomerEntityWithInvalidCpf()));
        assertNotNull(response);
        assertEquals("Cpf must contain 11 characters", response.getMessage());
    }

    @Test
    void validateSex_WhenSendInvalidSex_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () ->
                customerUpdateValidation.validateSex(CustomerFixture.getCustomerEntityWithInvalidSex()));
        assertNotNull(response);
        assertEquals("Invalid sex", response.getMessage());
    }

    @Test
    void validatePhone_WhenSendInvalidPhone_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () ->
                customerUpdateValidation.validatePhone(CustomerFixture.getCustomerEntityWithInvalidPhone()));
        assertNotNull(response);
        assertEquals("Phone must contain 11 characters", response.getMessage());
    }

}
