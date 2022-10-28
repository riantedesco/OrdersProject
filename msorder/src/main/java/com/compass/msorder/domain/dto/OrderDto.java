package com.compass.msorder.domain.dto;

import com.compass.msorder.util.constants.StatusOrderOption;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private Long number;

    private LocalDateTime dateTime;

    private Double total;

    private StatusOrderOption status;

    private ClientDto client;

    private List<ProductOrderDto> productOrders;

}
