package com.compass.mspayment.controller;

import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@ApiOperation(value = "Busca um pagamento pelo id do pedido e cpf do cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o pagamento encontrado"),
			@ApiResponse(code = 400, message = "Pagamento não encontrado"),
	})
	@GetMapping(value = "/find", produces = "application/json")
	public ResponseEntity<PaymentDto> findByIdAndCpfClient(@RequestParam Long idOrder, @RequestParam String cpfClient) {
		return ResponseEntity.ok(this.paymentService.findByIdOrderAndCpfClient(idOrder, cpfClient));
	}

}
