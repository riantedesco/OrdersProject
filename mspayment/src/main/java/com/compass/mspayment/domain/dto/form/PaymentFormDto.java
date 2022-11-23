package com.compass.mspayment.domain.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentFormDto {

    @ApiModelProperty(value = "Id do pedido")
    private Long idOrder;

    @ApiModelProperty(value = "Cpf do cliente")
    private String cpfCustomer;

    @ApiModelProperty(value = "Valor total do pedido")
    private Double totalOrder;

}
