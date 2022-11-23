package com.compass.msorder.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados da atualização do pedido")
public class OrderUpdateFormDto {

    @ApiModelProperty(value = "Número do pedido")
    @NotNull
    private Long number;

    @ApiModelProperty(value = "Id do cliente do pedido")
    @NotNull
    private Long idCustomer;

}
