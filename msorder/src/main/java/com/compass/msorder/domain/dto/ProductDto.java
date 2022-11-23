package com.compass.msorder.domain.dto;

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
@ApiModel(value = "Retorno dos dados do produto")
public class ProductDto {

    @ApiModelProperty(value = "Id do produto")
    private Long id;

    @ApiModelProperty(value = "Nome do produto")
    private String name;

    @ApiModelProperty(value = "Descrição do produto")
    private String description;

    @ApiModelProperty(value = "Marca do produto")
    private String brand;

    @ApiModelProperty(value = "Valor do produto")
    private Double price;

    @ApiModelProperty(value = "Status do produto (ativo ou inativo)")
    private Boolean active;

}
