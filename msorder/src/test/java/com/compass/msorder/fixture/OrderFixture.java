package com.compass.msorder.fixture;

import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.OrderUpdateDto;
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
                .idCustomer(CustomerFixture.getCustomerEntity().getId())
                .productOrders(productOrderFormDtos)
                .build();
    }

    public static OrderDto getOrderDto() {
        List<ProductOrderDto> productOrderDtos = List.of(ProductOrderFixture.getProductOrderDto());

        return OrderDto.builder()
                .id(1L)
                .number(1111L)
                .customer(CustomerFixture.getCustomerDto())
                .productOrders(productOrderDtos)
                .build();
    }

    public static OrderEntity getOrderEntity() {
        return OrderEntity.builder()
                .id(1L)
                .number(1111L)
                .customer(CustomerFixture.getCustomerEntity())
                .build();
    }

    public static OrderUpdateFormDto getOrderUpdateFormDto() {
        return OrderUpdateFormDto.builder()
                .number(5555L)
                .idCustomer(CustomerFixture.getCustomerEntity().getId())
                .build();
    }

    public static OrderFormDto getOrderFormDtoWithInvalidCustomer() {
        List<ProductOrderFormDto> productOrderFormDtos = List.of(ProductOrderFixture.getProductOrderFormDto());

        return OrderFormDto.builder()
                .number(1111L)
                .idCustomer(5000L)
                .productOrders(productOrderFormDtos)
                .build();
    }

    public static OrderUpdateFormDto getOrderUpdateFormDtoWithInvalidCustomer() {
        return OrderUpdateFormDto.builder()
                .number(5555L)
                .idCustomer(5000L)
                .build();
    }

    public static OrderUpdateDto getOrderUpdateDto() {
        return OrderUpdateDto.builder()
                .id(1L)
                .number(1111L)
                .customer(CustomerFixture.getCustomerDto())
                .build();
    }

    public static OrderFormDto getOrderFormDtoWithInvalidAttribute() {
        List<ProductOrderFormDto> productOrderFormDtos = List.of(ProductOrderFixture.getProductOrderFormDto());

        return OrderFormDto.builder()
                .number(1111L)
                .idCustomer(5000L)
                .productOrders(productOrderFormDtos)
                .build();
    }
}
