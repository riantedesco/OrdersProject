package com.compass.msorder.service.impl;

import com.compass.msorder.domain.ClientEntity;
import com.compass.msorder.domain.dto.ClientDto;
import com.compass.msorder.domain.dto.form.ClientFormDto;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.repository.ClientRepository;
import com.compass.msorder.service.ClientService;
import com.compass.msorder.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public ClientDto save(ClientFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		ClientEntity client = mapper.map(body, ClientEntity.class);
		validation.validateClient(client);
		ClientEntity clientResponse = this.clientRepository.save(client);
		return mapper.map(clientResponse, ClientDto.class);
	}

	@Override
	public List<ClientDto> list() {
		List<ClientDto> clients = this.clientRepository.findAll().stream().map(c -> mapper.map(c, ClientDto.class))
				.collect(Collectors.toList());
		return clients;
	}

	@Override
	public ClientDto find(Long id) {
		Optional<ClientEntity> client = this.clientRepository.findById(id);
		if (!client.isPresent()) {
			throw new NotFoundAttributeException("Client not found");
		}
		return mapper.map(client.get(), ClientDto.class);
	}

	@Override
	public ClientDto update(Long id, ClientFormDto body) {
		Optional<ClientEntity> client = this.clientRepository.findById(id);
		if (!client.isPresent()) {
			throw new NotFoundAttributeException("Client not found");
		}
		client.get().setCpf(body.getCpf());
		client.get().setName(body.getName());
		client.get().setSex(body.getSex());
		client.get().setBirthdate(body.getBirthdate());
		client.get().setEmail(body.getEmail());
		client.get().setPhone(body.getPhone());

		validation.validateClient(client.get());
		ClientEntity clientResponse = this.clientRepository.save(client.get());
		return mapper.map(clientResponse, ClientDto.class);
	}

	@Override
	public void delete(Long id) {
		Optional<ClientEntity> client = this.clientRepository.findById(id);
		if (!client.isPresent()) {
			throw new NotFoundAttributeException("Client not found");
		}
		this.clientRepository.deleteById(id);
	}

}
