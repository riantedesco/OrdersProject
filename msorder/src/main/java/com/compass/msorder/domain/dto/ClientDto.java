package com.compass.msorder.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {

    private Long id;

    private String cpf;

    private String name;

    private String sex;

    private LocalDate birthdate;

    private String email;

    private String phone;

}
