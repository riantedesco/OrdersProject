package com.compass.msorder.fixture;

import com.compass.msorder.domain.dto.form.ClientFormDto;

import java.time.LocalDate;

public class ClientFormDtoFixture {

    public static ClientFormDto getDefault() {
        ClientFormDto clientFormDto = ClientFormDto.builder()
                .cpf("000.000.000-00")
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();

        return clientFormDto;
    }

    public static ClientFormDto getWithInvalidCpf() {
        ClientFormDto clientFormDto = ClientFormDto.builder()
                .cpf("000.000.000-0")
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();

        return clientFormDto;
    }

    public static ClientFormDto getWithInvalidName() {
        ClientFormDto clientFormDto = ClientFormDto.builder()
                .cpf("000.000.000-00")
                .name("A")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();

        return clientFormDto;
    }

    public static ClientFormDto getWithInvalidSex() {
        ClientFormDto clientFormDto = ClientFormDto.builder()
                .cpf("000.000.000-00")
                .name("Client default")
                .sex("Helicóptero")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();

        return clientFormDto;
    }

    public static ClientFormDto getWithInvalidEmail() {
        ClientFormDto clientFormDto = ClientFormDto.builder()
                .cpf("000.000.000-00")
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("email")
                .phone("(99)99999-9999")
                .build();

        return clientFormDto;
    }

    public static ClientFormDto getWithInvalidPhone() {
        ClientFormDto clientFormDto = ClientFormDto.builder()
                .cpf("000.000.000-00")
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-999")
                .build();

        return clientFormDto;
    }
}
