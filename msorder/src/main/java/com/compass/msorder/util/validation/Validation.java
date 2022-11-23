package com.compass.msorder.util.validation;

import com.compass.msorder.domain.CustomerEntity;
import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.domain.ProductEntity;
import com.compass.msorder.domain.ProductOrderEntity;
import com.compass.msorder.util.validation.save.CustomerSaveValidation;
import com.compass.msorder.util.validation.save.OrderSaveValidation;
import com.compass.msorder.util.validation.update.CustomerUpdateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Validation {

	@Autowired
	private CustomerSaveValidation customerSaveValidation;

	@Autowired
	private OrderSaveValidation orderSaveValidation;

	@Autowired
	private CustomerUpdateValidation customerUpdateValidation;

	public void validateSaveCustomer(CustomerEntity customer) {
		customerSaveValidation.validateCpf(customer);
		customerSaveValidation.validateSex(customer);
		customerSaveValidation.validateBirthdate(customer);
		customerSaveValidation.validatePhone(customer);
	}

	public void validateSaveOrder(OrderEntity order) {
		orderSaveValidation.validateDateTime(order);
	}

	public void validateSaveProductOrder(ProductOrderEntity productOrder) {
	}

	public void validateSaveProduct(ProductEntity product) {
	}

	public void validateUpdateCustomer(CustomerEntity customer) {
		customerUpdateValidation.validateCpf(customer);
		customerUpdateValidation.validateSex(customer);
		customerUpdateValidation.validateBirthdate(customer);
		customerUpdateValidation.validatePhone(customer);
	}

	public void validateUpdateOrder(OrderEntity order) {
	}

	public void validateUpdateProduct(ProductEntity product) {
	}

}
