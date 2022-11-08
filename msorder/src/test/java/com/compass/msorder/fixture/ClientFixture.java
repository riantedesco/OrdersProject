package com.compass.msorder.fixture;

import com.compass.msorder.domain.ClientEntity;
import com.compass.msorder.domain.dto.ClientDto;
import com.compass.msorder.domain.dto.form.ClientFormDto;

import java.time.LocalDate;

public class ClientFixture {

    public static ClientEntity getClientEntity() {
        return ClientEntity.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static ClientFormDto getClientFormDto() {
        return ClientFormDto.builder()
                .cpf("000.000.000-00")
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static ClientDto getClientDto() {
        return ClientDto.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static ClientEntity getClientEntityWithInvalidCpf() {
        return ClientEntity.builder()
                .id(1L)
                .cpf("000.000.000-0")
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static ClientEntity getClientEntityWithInvalidName() {
        return ClientEntity.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("A")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static ClientEntity getClientEntityWithInvalidSex() {
        return ClientEntity.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("Client default")
                .sex("Helicóptero")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static ClientEntity getClientEntityWithInvalidEmail() {
        return ClientEntity.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("email")
                .phone("(99)99999-9999")
                .build();
    }

    public static ClientEntity getClientEntityWithInvalidPhone() {
        return ClientEntity.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-999")
                .build();
    }
}
