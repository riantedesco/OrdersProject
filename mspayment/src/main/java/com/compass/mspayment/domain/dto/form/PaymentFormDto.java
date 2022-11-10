package com.compass.mspayment.domain.dto.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentFormDto {

    private Long idOrder;

    private String cpfClient;

    private Double price;

}
