package com.compass.msorder.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductOrderDto {

    private Long id;

    private Integer quantity;

    private Double total;

    private ProductDto product;

}
