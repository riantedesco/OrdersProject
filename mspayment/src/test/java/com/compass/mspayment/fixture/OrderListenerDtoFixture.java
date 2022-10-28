package com.compass.mspayment.fixture;

import com.compass.mspayment.listener.order.dto.OrderListenerDto;
import com.compass.mspayment.util.constants.StatusOrderOption;

public class OrderListenerDtoFixture {

    public static OrderListenerDto getWithAuthorizedPrice() {
        OrderListenerDto orderListenerDto = OrderListenerDto.builder()
                .idOrder(1L)
                .cpfClient("000.000.000-00")
                .price(900.00)
                .status(StatusOrderOption.ORDER_CREATED)
                .build();

        return orderListenerDto;
    }

    public static OrderListenerDto getWithUnauthorizedPrice() {
        OrderListenerDto orderListenerDto = OrderListenerDto.builder()
                .idOrder(1L)
                .cpfClient("000.000.000-00")
                .price(1100.00)
                .status(StatusOrderOption.ORDER_CREATED)
                .build();

        return orderListenerDto;
    }
}
