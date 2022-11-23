package com.compass.msorder.controller;

import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.OrderUpdateDto;
import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.domain.dto.form.OrderUpdateFormDto;
import com.compass.msorder.service.OrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/orders")
@Api(value = "Operações do pedido")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "Cadastra um pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o pedido recém cadastrado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<OrderDto> save(@RequestBody @Valid OrderFormDto body) {
		return new ResponseEntity<>(this.orderService.save(body), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Busca um pedido pelo id, número do pedido e cpf do cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o pedido encontrado"),
			@ApiResponse(code = 404, message = "Pedido não encontrado")})
	@GetMapping(value = "/id={id}&number={number}&cpfClient={cpfClient}", produces = "application/json")
	public ResponseEntity<OrderDto> findByIdNumberAndCpfClient(@ApiParam(value = "Id do pedido", required = true, example = "1") @PathVariable Long id,
															   @ApiParam(value = "Número do pedido", required = true, example = "111111") @PathVariable Long number,
															   @ApiParam(value = "Cpf do cliente", required = true, example = "111.111.111-11") @PathVariable String cpfClient) {
		return ResponseEntity.ok(this.orderService.findByIdNumberAndCpfClient(id, number, cpfClient));
	}

	@ApiOperation(value = "Atualiza um pedido pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o pedido atualizado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Pedido não encontrado")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<OrderUpdateDto> update(@ApiParam(value = "Id do pedido", required = true, example = "1") @PathVariable Long id, @RequestBody @Valid OrderUpdateFormDto body) {
		return ResponseEntity.ok(this.orderService.update(id, body));
	}

}
