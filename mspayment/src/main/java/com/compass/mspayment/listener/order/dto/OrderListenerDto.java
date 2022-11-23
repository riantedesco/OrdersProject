package com.compass.mspayment.listener.order.dto;

import com.compass.mspayment.util.constants.StatusOrderOption;
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
@ApiModel(value = "Chegada dos dados do msorder")
public class OrderListenerDto {

    @ApiModelProperty(value = "Id do pedido")
    private Long idOrder;

    @ApiModelProperty(value = "Cpf do cliente")
    private String cpfCustomer;

    @ApiModelProperty(value = "Valor total do pedido")
    private Double totalOrder;

    @ApiModelProperty(value = "Status do pedido")
    private StatusOrderOption statusOrder;

}
