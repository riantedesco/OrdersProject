package com.compass.msorder.fixture;

import com.compass.msorder.domain.dto.form.ProductFormDto;

public class ProductFormDtoFixture {

    public static ProductFormDto getDefault() {
        ProductFormDto productFormDto = ProductFormDto.builder()
                .name("Product default")
                .description("Description default")
                .brand("Brand default")
                .price(300.00)
                .active(true)
                .build();

        return productFormDto;
    }

    public static ProductFormDto getWithInvalidPrice() {
        ProductFormDto productFormDto = ProductFormDto.builder()
                .name("Product default")
                .description("Description default")
                .brand("Brand default")
                .price(-300.00)
                .active(true)
                .build();

        return productFormDto;
    }

    public static ProductFormDto getWithInactiveProduct() {
        ProductFormDto productFormDto = ProductFormDto.builder()
                .name("Product default")
                .description("Description default")
                .brand("Brand default")
                .price(300.00)
                .active(false)
                .build();

        return productFormDto;
    }
}
