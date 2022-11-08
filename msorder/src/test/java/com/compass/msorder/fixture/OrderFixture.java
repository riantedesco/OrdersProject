package com.compass.msorder.fixture;

import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.domain.ProductOrderEntity;
import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.ProductOrderDto;
import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.domain.dto.form.OrderUpdateFormDto;
import com.compass.msorder.domain.dto.form.ProductOrderFormDto;

import java.util.List;

public class OrderFixture {

    public static OrderFormDto getOrderFormDto() {
        List<ProductOrderFormDto> productOrderFormDtos = List.of(ProductOrderFixture.getProductOrderFormDto());
        return OrderFormDto.builder()
                .number(1111L)
                .idClient(1L)
                .productOrders(productOrderFormDtos)
                .build();
    }

    public static OrderDto getOrderDto() {
        List<ProductOrderDto> productOrderDtos = List.of(ProductOrderFixture.getProductOrderDto());
        return OrderDto.builder()
                .id(1L)
                .number(1111L)
                .client(ClientFixture.getClientDto())
                .productOrders(productOrderDtos)
                .build();
    }

    public static OrderEntity getOrderEntity() {
        List<ProductOrderEntity> productOrders = List.of(ProductOrderFixture.getProductOrderEntity());
        return OrderEntity.builder()
                .id(1L)
                .number(1111L)
                .client(ClientFixture.getClientEntity())
                .productOrders(productOrders)
                .build();
    }

    public static OrderUpdateFormDto getOrderUpdateFormDto() {
        return OrderUpdateFormDto.builder()
                .number(5555L)
                .idClient(1L)
                .build();
    }

    public static OrderFormDto getOrderFormDtoWithInvalidClient() {
        List<ProductOrderFormDto> productOrderFormDtos = List.of(ProductOrderFixture.getProductOrderFormDto());
        return OrderFormDto.builder()
                .number(1111L)
                .idClient(500L)
                .productOrders(productOrderFormDtos)
                .build();
    }

    public static OrderUpdateFormDto getOrderUpdateFormDtoWithInvalidClient() {
        return OrderUpdateFormDto.builder()
                .number(5555L)
                .idClient(500L)
                .build();
    }
}
