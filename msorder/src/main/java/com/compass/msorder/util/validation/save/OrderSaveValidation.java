package com.compass.msorder.util.validation.save;

import com.compass.msorder.domain.OrderEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderSaveValidation {

	public void validateDateTime (OrderEntity order) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = LocalDateTime.now().format(dtf);
		order.setDateTime(LocalDateTime.parse(dataFormatada, dtf));
	}

}
