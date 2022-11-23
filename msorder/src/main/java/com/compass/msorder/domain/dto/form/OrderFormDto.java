package com.compass.msorder.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados do pedido")
public class OrderFormDto {

    @ApiModelProperty(value = "Número do pedido")
    @NotNull
    private Long number;

    @ApiModelProperty(value = "Id do cliente do pedido")
    @NotNull
    private Long idCustomer;

    @ApiModelProperty(value = "Itens do pedido")
    @NotNull
    private List<ProductOrderFormDto> productOrders;

}
