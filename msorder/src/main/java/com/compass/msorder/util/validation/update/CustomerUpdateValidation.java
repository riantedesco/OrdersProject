package com.compass.msorder.util.validation.update;

import com.compass.msorder.domain.CustomerEntity;
import com.compass.msorder.exception.InvalidAttributeException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerUpdateValidation {

	public void validateCpf(CustomerEntity customer) {
		String cpf = customer.getCpf().replaceAll("\\D", "");
		long countCharCpf = cpf.chars().filter(ch -> ch != ' ').count();
		if (countCharCpf < 11 || countCharCpf > 11) {
			throw new InvalidAttributeException("Cpf must contain 11 characters");
		}
		try {
			MaskFormatter mask = new MaskFormatter("###.###.###-##");
			JFormattedTextField cpfFormatado = new JFormattedTextField(mask);
			cpfFormatado.setText(cpf);
			customer.setCpf(cpfFormatado.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void validateSex(CustomerEntity customer) {
		String masculino = "Masculino";
		String feminino = "Feminino";
		List<String> list = Arrays.asList(masculino, feminino);
		if (!list.contains(customer.getSex())) {
			throw new InvalidAttributeException("Invalid sex");
		}
	}

	public void validateBirthdate(CustomerEntity customer) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = customer.getBirthdate().format(formatter);
		customer.setBirthdate(LocalDate.parse(dataFormatada, formatter));
	}

	public void validatePhone(CustomerEntity customer) {
		String phone = customer.getPhone().replaceAll("\\D", "");
		long countCharPhone = phone.chars().filter(ch -> ch != ' ').count();
		if (countCharPhone < 11 || countCharPhone > 11) {
			throw new InvalidAttributeException("Phone must contain 11 characters");
		}
		try {
			MaskFormatter mask = new MaskFormatter("(##)#####-####");
			JFormattedTextField phoneFormatado = new JFormattedTextField(mask);
			phoneFormatado.setText(phone);
			customer.setPhone(phoneFormatado.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
