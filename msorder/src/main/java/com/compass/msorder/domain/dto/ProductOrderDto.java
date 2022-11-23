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
@ApiModel(value = "Retorno dos dados do item")
public class ProductOrderDto {

    @ApiModelProperty(value = "Id do item")
    private Long id;

    @ApiModelProperty(value = "Quantidade do item")
    private Integer quantity;

    @ApiModelProperty(value = "Valor total do item")
    private Double total;

    @ApiModelProperty(value = "Produto do item")
    private ProductDto product;

}
