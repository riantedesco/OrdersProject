package com.compass.msorder.listener.payment.dto;

import com.compass.msorder.util.constants.StatusOrderOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentListenerDto {

    private Long idPayment;

    private Long idOrder;

    private StatusOrderOption status;

}
