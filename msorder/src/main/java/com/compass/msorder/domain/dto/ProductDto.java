package com.compass.msorder.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private String brand;

    private Double price;

    private Boolean active;

}
