package com.compass.msorder.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados do produto")
public class ProductFormDto {

    @ApiModelProperty(value = "Nome do produto")
    @NotNull
    @Size(min = 3, message = "Name must contain at least 3 characters")
    private String name;

    @ApiModelProperty(value = "Descrição do produto")
    @NotNull
    @Size(min = 3, message = "Description must contain at least 3 characters")
    private String description;

    @ApiModelProperty(value = "Marca do produto")
    @NotNull
    @Size(min = 3, message = "Brand must contain at least 3 characters")
    private String brand;

    @ApiModelProperty(value = "Valor do produto")
    @NotNull
    @PositiveOrZero(message = "Price should be positive or zero")
    private Double price;

    @ApiModelProperty(value = "Status do produto (ativo ou inativo)")
    @NotNull
    private Boolean active;

}
