package com.compass.msorder.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cpf;

    @NotNull
    private String name;

    @NotNull
    private String sex;

    @NotNull
    private LocalDate birthdate;

    @NotNull
    private String email;

    @NotNull
    private String phone;

}
