package com.compass.msorder.service;

import com.compass.msorder.domain.dto.ClientDto;
import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.ClientFormDtoFixture;
import com.compass.msorder.fixture.ProductFormDtoFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void shouldNotSave ()  {
        Exception exceptionPrice = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.productService.save(ProductFormDtoFixture.getWithInvalidPrice());
        });
        Assertions.assertTrue(exceptionPrice.getMessage().contains("Price must not be less than 0"));
    }

    @Test
    void shouldNotFind ()  {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.productService.find(5000L);
        });
        Assertions.assertTrue(exception.getMessage().contains("Product not found"));
    }

    @Test
    void shouldNotUpdate () {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.productService.update(5000L, ProductFormDtoFixture.getDefault());
        });
        Assertions.assertTrue(exception.getMessage().contains("Product not found"));
    }

    @Test
    void shouldNotDelete () {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.productService.delete(5000L);
        });
        Assertions.assertTrue(exception.getMessage().contains("Product not found"));
    }
}
