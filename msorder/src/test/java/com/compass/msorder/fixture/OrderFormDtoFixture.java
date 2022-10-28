package com.compass.msorder.fixture;

import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.domain.dto.form.ProductOrderFormDto;

import java.util.List;

public class OrderFormDtoFixture {

    public static OrderFormDto getDefault() {
        List<ProductOrderFormDto> productOrderFormDtos = List.of(ProductOrderFormDtoFixture.getDefault());
        OrderFormDto orderFormDto = OrderFormDto.builder()
                .number(1111L)
                .idClient(1L)
                .productOrders(productOrderFormDtos)
                .build();

        return orderFormDto;
    }
}
