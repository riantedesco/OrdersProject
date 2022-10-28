package com.compass.msorder.service;

import com.compass.msorder.domain.dto.ClientDto;
import com.compass.msorder.domain.dto.form.ClientFormDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientFormDto body);

    List<ClientDto> list();

    ClientDto find(Long id);

    ClientDto update(Long id, ClientFormDto body);

    void delete(Long id);

}
