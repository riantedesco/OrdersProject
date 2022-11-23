package com.compass.msorder.service;

import com.compass.msorder.domain.dto.CustomerDto;
import com.compass.msorder.domain.dto.form.CustomerFormDto;

import java.util.List;

public interface CustomerService {

    CustomerDto save(CustomerFormDto body);

    List<CustomerDto> list();

    CustomerDto find(Long id);

    CustomerDto update(Long id, CustomerFormDto body);

    void delete(Long id);

}
