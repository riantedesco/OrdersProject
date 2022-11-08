package com.compass.msorder.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrderDto {

    private Long id;

    private Integer quantity;

    private Double total;

    private ProductDto product;

}
