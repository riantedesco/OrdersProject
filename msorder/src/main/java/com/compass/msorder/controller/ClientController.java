package com.compass.msorder.controller;

import com.compass.msorder.domain.dto.ClientDto;
import com.compass.msorder.domain.dto.form.ClientFormDto;
import com.compass.msorder.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@ApiOperation(value = "Cadastra um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente recém cadastrado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção, causada por um campo nulo"),
	})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<ClientDto> save(@RequestBody @Valid ClientFormDto body) {
		return ResponseEntity.ok(this.clientService.save(body));
	}

	@ApiOperation(value = "Lista os clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna os clientes cadastrados"),
	})
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<ClientDto>> list() {
		return ResponseEntity.ok(this.clientService.list());
	}

	@ApiOperation(value = "Busca um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente encontrado"),
			@ApiResponse(code = 400, message = "Cliente não encontrado"),
	})
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<ClientDto> find(@PathVariable Long id) {
		return ResponseEntity.ok(this.clientService.find(id));
	}

	@ApiOperation(value = "Atualiza um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente atualizado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados ou cliente não encontrado"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção, causada por um campo nulo"),
	})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody @Valid ClientFormDto body) {
		return ResponseEntity.ok(this.clientService.update(id, body));
	}

	@ApiOperation(value = "Remove um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente deletado"),
			@ApiResponse(code = 400, message = "Cliente não encontrado"),
	})
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		this.clientService.delete(id);
		return ResponseEntity.ok().build();
	}

}
