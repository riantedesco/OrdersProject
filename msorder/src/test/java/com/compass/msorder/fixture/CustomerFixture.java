package com.compass.msorder.fixture;

import com.compass.msorder.domain.CustomerEntity;
import com.compass.msorder.domain.dto.CustomerDto;
import com.compass.msorder.domain.dto.form.CustomerFormDto;

import java.time.LocalDate;

public class CustomerFixture {

    public static CustomerEntity getCustomerEntity() {
        return CustomerEntity.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("Customer default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static CustomerFormDto getCustomerFormDto() {
        return CustomerFormDto.builder()
                .cpf("000.000.000-00")
                .name("Customer default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static CustomerDto getCustomerDto() {
        return CustomerDto.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("Customer default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static CustomerEntity getCustomerEntityWithInvalidCpf() {
        return CustomerEntity.builder()
                .id(1L)
                .cpf("000.000.000-0")
                .name("Customer default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static CustomerEntity getCustomerEntityWithInvalidName() {
        return CustomerEntity.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("A")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static CustomerEntity getCustomerEntityWithInvalidSex() {
        return CustomerEntity.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("Customer default")
                .sex("Helicóptero")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-9999")
                .build();
    }

    public static CustomerEntity getCustomerEntityWithInvalidEmail() {
        return CustomerEntity.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("Customer default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("email")
                .phone("(99)99999-9999")
                .build();
    }

    public static CustomerEntity getCustomerEntityWithInvalidPhone() {
        return CustomerEntity.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .name("Customer default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default@email.com")
                .phone("(99)99999-999")
                .build();
    }

    public static CustomerFormDto getCustomerFormDtoWithInvalidAttribute() {
        return CustomerFormDto.builder()
                .cpf("000.000.000-0")
                .name("A")
                .sex("Helicóptero")
                .birthdate(LocalDate.parse("2002-03-30"))
                .email("default")
                .phone("(99)99999-999")
                .build();
    }
}
