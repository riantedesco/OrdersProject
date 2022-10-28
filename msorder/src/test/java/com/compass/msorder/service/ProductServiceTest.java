package com.compass.msorder.service;

import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.ProductFormDtoFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void saveProduct_WhenSendSaveWithInvalidPrice_ExpectedInvalidAttributeException ()  {
        Exception exceptionPrice = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.productService.save(ProductFormDtoFixture.getWithInvalidPrice());
        });
        Assertions.assertTrue(exceptionPrice.getMessage().contains("Price must not be less than 0"));
    }

    @Test
    void findProduct_WhenSendFindByIdWithNotFoundProduct_ExpectedNotFoundAttributeException ()  {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.productService.find(5000L);
        });
        Assertions.assertTrue(exception.getMessage().contains("Product not found"));
    }

    @Test
    void updateProduct_WhenSendUpdateByIdWithNotFoundProduct_ExpectedNotFoundAttributeException () {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.productService.update(5000L, ProductFormDtoFixture.getDefault());
        });
        Assertions.assertTrue(exception.getMessage().contains("Product not found"));
    }

    @Test
    void deleteProduct_WhenSendDeleteByIdWithNotFoundProduct_ExpectedNotFoundAttributeException () {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.productService.delete(5000L);
        });
        Assertions.assertTrue(exception.getMessage().contains("Product not found"));
    }
}
