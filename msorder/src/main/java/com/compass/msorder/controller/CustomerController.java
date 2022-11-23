package com.compass.msorder.controller;

import com.compass.msorder.domain.dto.CustomerDto;
import com.compass.msorder.domain.dto.form.CustomerFormDto;
import com.compass.msorder.service.CustomerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@Api(value = "Operações do cliente")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "Cadastra um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o cliente recém cadastrado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<CustomerDto> save(@RequestBody @Valid CustomerFormDto body) {
		return new ResponseEntity<>(this.customerService.save(body), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Lista os clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna os clientes cadastrados")})
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<CustomerDto>> list() {
		return ResponseEntity.ok(this.customerService.list());
	}

	@ApiOperation(value = "Busca um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente encontrado"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")})
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<CustomerDto> find(@ApiParam(value = "Id do cliente", required = true, example = "1") @PathVariable Long id) {
		return ResponseEntity.ok(this.customerService.find(id));
	}

	@ApiOperation(value = "Atualiza um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente atualizado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<CustomerDto> update(@ApiParam(value = "Id do cliente", required = true, example = "1") @PathVariable Long id, @RequestBody @Valid CustomerFormDto body) {
		return ResponseEntity.ok(this.customerService.update(id, body));
	}

	@ApiOperation(value = "Remove um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Cliente deletado"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")})
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> delete(@ApiParam(value = "Id do cliente", required = true, example = "1") @PathVariable Long id) {
		this.customerService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
