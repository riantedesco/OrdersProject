package com.compass.mspayment.controller;

import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.service.PaymentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
@Api(value = "Operações do pagamento")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@ApiOperation(value = "Busca um pagamento pelo id do pedido e cpf do cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o pagamento encontrado"),
			@ApiResponse(code = 404, message = "Pagamento não encontrado")})
	@GetMapping(value = "/find", produces = "application/json")
	public ResponseEntity<PaymentDto> findByIdOrderAndCpfClient(@ApiParam(value = "Id do pedido", required = true, example = "1") @RequestParam Long idOrder,
																@ApiParam(value = "Cpf do cliente", required = true, example = "1") @RequestParam String cpfCustomer) {
		return ResponseEntity.ok(this.paymentService.findByIdOrderAndCpfCustomer(idOrder, cpfCustomer));
	}

}
