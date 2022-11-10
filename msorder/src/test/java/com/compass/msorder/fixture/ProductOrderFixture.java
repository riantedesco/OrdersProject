package com.compass.msorder.fixture;

import com.compass.msorder.domain.ProductOrderEntity;
import com.compass.msorder.domain.dto.ProductOrderDto;
import com.compass.msorder.domain.dto.form.ProductOrderFormDto;

public class ProductOrderFixture {

    public static ProductOrderFormDto getProductOrderFormDto() {
        return ProductOrderFormDto.builder()
                .quantity(2)
                .idProduct(1L)
                .build();
    }

    public static ProductOrderDto getProductOrderDto() {
        return ProductOrderDto.builder()
                .id(1L)
                .quantity(2)
                .total(600.00)
                .product(ProductFixture.getProductDto())
                .build();
    }

    public static ProductOrderEntity getProductOrderEntity() {
        return ProductOrderEntity.builder()
                .id(1L)
                .quantity(2)
                .total(600.00)
                .product(ProductFixture.getProductEntity())
                .order(OrderFixture.getOrderEntity())
                .build();
    }

    public static ProductOrderFormDto getProductOrderFormWithInvalidIdProduct() {
        return ProductOrderFormDto.builder()
                .quantity(2)
                .idProduct(500L)
                .build();
    }

    public static ProductOrderFormDto getProductOrderFormWithInactiveProduct() {
        return ProductOrderFormDto.builder()
                .quantity(2)
                .idProduct(ProductFixture.getProductEntityWithInactiveProduct().getId())
                .build();
    }

    public static ProductOrderEntity getProductOrderFormWithInvalidQuantity() {
        return ProductOrderEntity.builder()
                .quantity(-2)
                .product(ProductFixture.getProductEntity())
                .build();
    }
}
