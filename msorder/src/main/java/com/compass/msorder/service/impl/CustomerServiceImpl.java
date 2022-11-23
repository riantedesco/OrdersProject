package com.compass.msorder.service.impl;

import com.compass.msorder.domain.CustomerEntity;
import com.compass.msorder.domain.dto.CustomerDto;
import com.compass.msorder.domain.dto.form.CustomerFormDto;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.repository.CustomerRepository;
import com.compass.msorder.service.CustomerService;
import com.compass.msorder.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public CustomerDto save(CustomerFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		CustomerEntity customer = mapper.map(body, CustomerEntity.class);
		validation.validateSaveCustomer(customer);
		CustomerEntity response = this.customerRepository.save(customer);
		return mapper.map(response, CustomerDto.class);
	}

	@Override
	public List<CustomerDto> list() {
		List<CustomerDto> customers = this.customerRepository.findAll().stream().map(c -> mapper.map(c, CustomerDto.class))
				.collect(Collectors.toList());
		return customers;
	}

	@Override
	public CustomerDto find(Long id) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer not found");
		}
		return mapper.map(customer.get(), CustomerDto.class);
	}

	@Override
	public CustomerDto update(Long id, CustomerFormDto body) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer not found");
		}
		customer.get().setCpf(body.getCpf());
		customer.get().setName(body.getName());
		customer.get().setSex(body.getSex());
		customer.get().setBirthdate(body.getBirthdate());
		customer.get().setEmail(body.getEmail());
		customer.get().setPhone(body.getPhone());

		validation.validateUpdateCustomer(customer.get());
		CustomerEntity response = this.customerRepository.save(customer.get());
		return mapper.map(response, CustomerDto.class);
	}

	@Override
	public void delete(Long id) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer not found");
		}
		this.customerRepository.deleteById(id);
	}

}
