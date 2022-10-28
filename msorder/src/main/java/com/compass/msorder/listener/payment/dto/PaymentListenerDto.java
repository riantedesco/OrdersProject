package com.compass.msorder.listener.payment.dto;

import com.compass.msorder.util.constants.StatusOrderOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentListenerDto {

    private Long idPayment;

    private Long idOrder;

    private StatusOrderOption status;

}
