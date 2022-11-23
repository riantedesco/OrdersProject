package com.compass.msorder.listener.payment.dto;

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
@ApiModel(value = "Chegada dos dados do mspayment")
public class PaymentListenerDto {

    @ApiModelProperty(value = "Id do pagamento")
    private Long idPayment;

    @ApiModelProperty(value = "Id do pedido")
    private Long idOrder;

    @ApiModelProperty(value = "Status do pedido")
    private StatusOrderOption statusOrder;

}
