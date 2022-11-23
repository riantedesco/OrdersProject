package com.compass.msorder.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados do cliente")
public class CustomerFormDto {

    @ApiModelProperty(value = "Cpf do cliente")
    @NotNull
    private String cpf;

    @ApiModelProperty(value = "Nome do cliente")
    @NotNull
    @Size(min = 3, message = "Name must contain at least 3 characters")
    private String name;

    @ApiModelProperty(value = "Sexo do cliente")
    @NotNull
    private String sex;

    @ApiModelProperty(value = "Data de nascimento do cliente")
    @NotNull
    private LocalDate birthdate;

    @ApiModelProperty(value = "Email do cliente")
    @NotNull
    @Email(message = "Email must be valid")
    private String email;

    @ApiModelProperty(value = "Telefone celular do cliente")
    @NotNull
    private String phone;

}
