package com.compass.msorder.controller;

import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.OrderUpdateDto;
import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.domain.dto.form.OrderUpdateFormDto;
import com.compass.msorder.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "Cadastra um pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o pedido recém cadastrado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 500, message = "Corpo da requisição está incorreto")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<OrderDto> save(@RequestBody @Valid OrderFormDto body) {
		return new ResponseEntity<>(this.orderService.save(body), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Busca um pedido pelo id, número do pedido e cpf do cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o pedido encontrado"),
			@ApiResponse(code = 404, message = "Pedido não encontrado")})
	@GetMapping(value = "/find", produces = "application/json")
	public ResponseEntity<OrderDto> findByIdNumberAndCpfClient(@RequestParam Long id, @RequestParam Long number, @RequestParam String cpfClient) {
		return ResponseEntity.ok(this.orderService.findByIdNumberAndCpfClient(id, number, cpfClient));
	}

	@ApiOperation(value = "Atualiza um pedido pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o pedido atualizado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Pedido não encontrado"),
			@ApiResponse(code = 500, message = "Corpo da requisição está incorreto")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<OrderUpdateDto> update(@PathVariable Long id, @RequestBody @Valid OrderUpdateFormDto body) {
		return ResponseEntity.ok(this.orderService.update(id, body));
	}

}
