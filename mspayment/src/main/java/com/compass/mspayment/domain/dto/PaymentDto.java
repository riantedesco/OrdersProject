package com.compass.mspayment.domain.dto;

import com.compass.mspayment.util.constants.StatusOrderOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDto {

    private Long id;

    private Long idOrder;

    private String cpfClient;

    private Double price;

    private StatusOrderOption status;

}
