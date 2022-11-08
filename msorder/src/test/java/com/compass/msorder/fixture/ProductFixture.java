package com.compass.msorder.fixture;

import com.compass.msorder.domain.ProductEntity;
import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.domain.dto.form.ProductFormDto;

public class ProductFixture {

    public static ProductFormDto getProductFormDto() {
        return ProductFormDto.builder()
                .name("Product default")
                .description("Description default")
                .brand("Brand default")
                .price(300.00)
                .active(true)
                .build();
    }

    public static ProductDto getProductDto() {
        return ProductDto.builder()
                .id(1L)
                .name("Product default")
                .description("Description default")
                .brand("Brand default")
                .price(300.00)
                .active(true)
                .build();
    }

    public static ProductEntity getProductEntity() {
        return ProductEntity.builder()
                .id(1L)
                .name("Product default")
                .description("Description default")
                .brand("Brand default")
                .price(300.00)
                .active(true)
                .build();
    }

    public static ProductEntity getProductFormWithInvalidPrice() {
        return ProductEntity.builder()
                .name("Product default")
                .description("Description default")
                .brand("Brand default")
                .price(-300.00)
                .active(true)
                .build();
    }

    public static ProductFormDto getProductFormWithInactiveProduct() {
        return ProductFormDto.builder()
                .name("Product default")
                .description("Description default")
                .brand("Brand default")
                .price(300.00)
                .active(false)
                .build();
    }
}
