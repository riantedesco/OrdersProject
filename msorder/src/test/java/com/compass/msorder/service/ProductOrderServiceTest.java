package com.compass.msorder.service;

import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.exception.InactiveProductException;
import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.ProductFormDtoFixture;
import com.compass.msorder.fixture.ProductOrderFormDtoFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductOrderServiceTest {

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private ProductService productService;

    @Test
    void shouldNotSave ()  {
        Exception exceptionIdProduct = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.productOrderService.save(ProductOrderFormDtoFixture.getWithInvalidIdProduct());
        });

        Exception exceptionActiveProduct = Assertions.assertThrows(InactiveProductException.class, () -> {
            ProductDto product = this.productService.save(ProductFormDtoFixture.getWithInactiveProduct());
            this.productOrderService.save(ProductOrderFormDtoFixture.getWithInactiveProduct(product.getId()));
        });

        Exception exceptionQuantity = Assertions.assertThrows(InvalidAttributeException.class, () -> {
            this.productService.save(ProductFormDtoFixture.getDefault());
            this.productOrderService.save(ProductOrderFormDtoFixture.getWithInvalidQuantity());
        });

        Assertions.assertTrue(exceptionIdProduct.getMessage().contains("Product not found"));
        Assertions.assertTrue(exceptionActiveProduct.getMessage().contains("Product inactive"));
        Assertions.assertTrue(exceptionQuantity.getMessage().contains("Quantity must not be less than or equal to 0"));
    }

}
