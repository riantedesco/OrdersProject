package com.compass.mspayment.listener.order.dto;

import com.compass.mspayment.util.constants.StatusOrderOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderListenerDto {

    private Long idOrder;

    private String cpfClient;

    private Double price;

    private StatusOrderOption status;

}
