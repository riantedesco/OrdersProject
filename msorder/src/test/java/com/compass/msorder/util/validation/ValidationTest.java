package com.compass.msorder.util.validation;

import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.fixture.ClientFixture;
import com.compass.msorder.fixture.ProductFixture;
import com.compass.msorder.fixture.ProductOrderFixture;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class ValidationTest {

    @InjectMocks
    private Validation validation;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateClient_WhenSendInvalidCpf_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () ->
                validation.validateClient(ClientFixture.getClientEntityWithInvalidCpf()));
        assertNotNull(response);
        assertEquals("Cpf must  11 characters", response.getMessage());
    }

    @Test
    void validateClient_WhenSendInvalidName_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () ->
            validation.validateClient(ClientFixture.getClientEntityWithInvalidName()));
        assertNotNull(response);
        assertEquals("Name must contain at least 3 characters", response.getMessage());
    }

    @Test
    void validateClient_WhenSendInvalidSex_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () ->
                validation.validateClient(ClientFixture.getClientEntityWithInvalidSex()));
        assertNotNull(response);
        assertEquals("Invalid sex", response.getMessage());
    }

    @Test
    void validateClient_WhenSendInvalidEmail_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () ->
                validation.validateClient(ClientFixture.getClientEntityWithInvalidEmail()));
        assertNotNull(response);
        assertEquals("Invalid email", response.getMessage());
    }

    @Test
    void validateClient_WhenSendInvalidPhone_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () ->
            validation.validateClient(ClientFixture.getClientEntityWithInvalidPhone()));
        assertNotNull(response);
        assertEquals("Phone must contain 11 characters", response.getMessage());
    }

    @Test
    void validateProduct_WhenSendInvalidPrice_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = Assertions.assertThrows(InvalidAttributeException.class, () ->
            validation.validateProduct(ProductFixture.getProductFormWithInvalidPrice()));
        assertNotNull(response);
        assertEquals("Price must not be less than 0", response.getMessage());
    }

    @Test
    void validateProductOrder_WhenSendInvalidQuantity_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = Assertions.assertThrows(InvalidAttributeException.class, () ->
            validation.validateProductOrder(ProductOrderFixture.getProductOrderFormWithInvalidQuantity()));
        assertNotNull(response);
        assertEquals("Quantity must not be less than or equal to 0", response.getMessage());
    }
}
