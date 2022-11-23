package com.compass.msorder.domain.dto;

import com.compass.msorder.util.constants.StatusOrderOption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Retorno dos dados da atualização do pedido")
public class OrderUpdateDto {

    @ApiModelProperty(value = "Id do pedido")
    private Long id;

    @ApiModelProperty(value = "Número do pedido")
    private Long number;

    @ApiModelProperty(value = "Data e hora do pedido")
    private LocalDateTime dateTime;

    @ApiModelProperty(value = "Valor total do pedido")
    private Double total;

    @ApiModelProperty(value = "Status do pedido")
    private StatusOrderOption status;

    @ApiModelProperty(value = "Cliente do pedido")
    private CustomerDto customer;

}
