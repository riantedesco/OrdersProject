package com.compass.msorder.domain.dto.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFormDto {

    private Long number;

    private Long idClient;

    private List<ProductOrderFormDto> productOrders;

}
