package com.compass.msorder.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private String brand;

    private Double price;

    private Boolean active;

}
