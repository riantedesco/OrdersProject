package com.compass.msorder.publisher.payment.dto;

import com.compass.msorder.util.constants.StatusOrderOption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados para o mspayment")
public class PaymentPublisherDto {

    @ApiModelProperty(value = "Id do pedido")
    private Long idOrder;

    @ApiModelProperty(value = "Cpf do cliente")
    private String cpfCustomer;

    @ApiModelProperty(value = "Valor total do pedido")
    private Double totalOrder;

    @ApiModelProperty(value = "Status do pedido")
    private StatusOrderOption statusOrder;

}
