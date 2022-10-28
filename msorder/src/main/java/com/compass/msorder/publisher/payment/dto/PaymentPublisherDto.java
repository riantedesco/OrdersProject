package com.compass.msorder.publisher.payment.dto;

import com.compass.msorder.util.constants.StatusOrderOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPublisherDto {

    private Long idOrder;

    private String cpfClient;

    private Double price;

    private StatusOrderOption status;

}
