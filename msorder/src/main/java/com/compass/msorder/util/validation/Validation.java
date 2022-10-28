package com.compass.msorder.util.validation;

import com.compass.msorder.domain.ClientEntity;
import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.domain.ProductEntity;
import com.compass.msorder.domain.ProductOrderEntity;
import com.compass.msorder.exception.InvalidAttributeException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Validation {
	public void validateClient (ClientEntity client) {
		//validate cpf
		String cpf = client.getCpf().replaceAll("\\D", "");
		long countCharCpf = cpf.chars().filter(ch -> ch != ' ').count();
		if (countCharCpf < 11 || countCharCpf > 11) {
			throw new InvalidAttributeException("Cpf must  11 characters");
		}
		try {
			MaskFormatter mask = new MaskFormatter("###.###.###-##");
			JFormattedTextField cpfFormatado = new JFormattedTextField(mask);
			cpfFormatado.setText(cpf);
			client.setCpf(cpfFormatado.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//validate name
		long countCharName = client.getName().chars().filter(ch -> ch != ' ').count();
		if (countCharName < 3) {
			throw new InvalidAttributeException("Name must contain at least 3 characters");
		}

		//valida sex
		String masculino = "Masculino";
		String feminino = "Feminino";
		List<String> list = Arrays.asList(masculino, feminino);
		if (!list.contains(client.getSex())) {
			throw new InvalidAttributeException("Invalid sex");
		}

		//valida birthdate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = client.getBirthdate().format(formatter);
		client.setBirthdate(LocalDate.parse(dataFormatada, formatter));

		//valida email
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(client.getEmail());
		if (!matcher.matches()) {
			throw new InvalidAttributeException("Invalid email");
		}

		//validate phone
		String phone = client.getPhone().replaceAll("\\D", "");
		long countCharPhone = phone.chars().filter(ch -> ch != ' ').count();
		if (countCharPhone < 11 || countCharPhone > 11) {
			throw new InvalidAttributeException("Phone must contain 11 characters");
		}
		try {
			MaskFormatter mask = new MaskFormatter("(##)#####-####");
			JFormattedTextField phoneFormatado = new JFormattedTextField(mask);
			phoneFormatado.setText(phone);
			client.setPhone(phoneFormatado.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	public void validateOrder (OrderEntity order) {
		//validate dateTime
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = LocalDateTime.now().format(dtf);
		order.setDateTime(LocalDateTime.parse(dataFormatada, dtf));
	}

	public void validateProduct (ProductEntity product) {
		//validate price
		if (product.getPrice() < 0) {
			throw new InvalidAttributeException("Price must not be less than 0");
		}
	}
	public void validateProductOrder (ProductOrderEntity productOrder) {
		//validate quantity
		if (productOrder.getQuantity() <= 0) {
			throw new InvalidAttributeException("Quantity must not be less than or equal to 0");
		}
	}

}
