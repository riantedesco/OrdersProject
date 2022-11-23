package com.compass.mspayment.publisher.order.dto;

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
@ApiModel(value = "Envio dos dados para o msorder")
public class OrderPublisherDto {

    @ApiModelProperty(value = "Id do pagamento")
    private Long idPayment;

    @ApiModelProperty(value = "Id do pedido")
    private Long idOrder;

    @ApiModelProperty(value = "Status do pedido")
    private StatusOrderOption statusOrder;

}
