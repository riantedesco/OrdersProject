package com.compass.mspayment.fixture.order;

import com.compass.mspayment.listener.order.dto.OrderListenerDto;
import com.compass.mspayment.util.constants.StatusOrderOption;

public class OrderFixture {

    public static OrderListenerDto getOrderListenerDtoWithAuthorizedPrice() {
        return OrderListenerDto.builder()
                .idOrder(1L)
                .cpfClient("000.000.000-00")
                .price(900.00)
                .status(StatusOrderOption.ORDER_CREATED)
                .build();
    }

    public static OrderListenerDto getOrderListenerDtoWithUnauthorizedPrice() {
        return OrderListenerDto.builder()
                .idOrder(1L)
                .cpfClient("000.000.000-00")
                .price(1100.00)
                .status(StatusOrderOption.ORDER_CREATED)
                .build();
    }
}
