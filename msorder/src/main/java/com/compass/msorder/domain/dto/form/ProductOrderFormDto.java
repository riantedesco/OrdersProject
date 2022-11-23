package com.compass.msorder.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados do item")
public class ProductOrderFormDto {

    @ApiModelProperty(value = "Quantidade do item")
    @NotNull
    @Positive(message = "Quantity should be positive")
    private Integer quantity;

    @ApiModelProperty(value = "Id do produto do item")
    @NotNull
    private Long idProduct;

}
