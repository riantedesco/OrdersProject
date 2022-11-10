package com.compass.mspayment.publisher.order.dto;

import com.compass.mspayment.util.constants.StatusOrderOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPublisherDto {

    private Long idPayment;

    private Long idOrder;

    private StatusOrderOption status;

}
