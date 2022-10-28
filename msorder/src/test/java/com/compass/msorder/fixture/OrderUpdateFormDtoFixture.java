package com.compass.msorder.fixture;

import com.compass.msorder.domain.dto.form.OrderUpdateFormDto;

public class OrderUpdateFormDtoFixture {

    public static OrderUpdateFormDto getDefault() {
        OrderUpdateFormDto orderUpdateFormDto = OrderUpdateFormDto.builder()
                .number(5555L)
                .idClient(1L)
                .build();

        return orderUpdateFormDto;
    }
}
