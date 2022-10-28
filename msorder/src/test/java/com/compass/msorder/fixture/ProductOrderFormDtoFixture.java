package com.compass.msorder.fixture;

import com.compass.msorder.domain.dto.form.ProductFormDto;
import com.compass.msorder.domain.dto.form.ProductOrderFormDto;

public class ProductOrderFormDtoFixture {

    public static ProductOrderFormDto getDefault() {
        ProductOrderFormDto productOrderFormDto = ProductOrderFormDto.builder()
                .quantity(2)
                .idProduct(1L)
                .build();

        return productOrderFormDto;
    }

    public static ProductOrderFormDto getWithInvalidIdProduct() {
        ProductOrderFormDto productOrderFormDto = ProductOrderFormDto.builder()
                .quantity(2)
                .idProduct(500L)
                .build();

        return productOrderFormDto;
    }

    public static ProductOrderFormDto getWithInactiveProduct(Long id) {
        ProductOrderFormDto productOrderFormDto = ProductOrderFormDto.builder()
                .quantity(2)
                .idProduct(id)
                .build();

        return productOrderFormDto;
    }

    public static ProductOrderFormDto getWithInvalidQuantity() {
        ProductOrderFormDto productOrderFormDto = ProductOrderFormDto.builder()
                .quantity(-2)
                .idProduct(2L)
                .build();

        return productOrderFormDto;
    }
}
