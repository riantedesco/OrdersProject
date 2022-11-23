package com.compass.msorder.controller;

import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.domain.dto.form.ProductFormDto;
import com.compass.msorder.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
@Api(value = "Operações do produto")
public class ProductController {

	@Autowired
	private ProductService productService;

	@ApiOperation(value = "Cadastra um produto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o produto recém cadastrado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<ProductDto> save(@RequestBody @Valid ProductFormDto body) {
		return new ResponseEntity<>(this.productService.save(body), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Lista os produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna os produtos cadastrados")})
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<ProductDto>> list() {
		return ResponseEntity.ok(this.productService.list());
	}

	@ApiOperation(value = "Busca um produto pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o produto encontrado"),
			@ApiResponse(code = 404, message = "Produto não encontrado")})
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<ProductDto> find(@ApiParam(value = "Id do produto", required = true, example = "1") @PathVariable Long id) {
		return ResponseEntity.ok(this.productService.find(id));
	}

	@ApiOperation(value = "Atualiza um produto pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o produto atualizado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Produto não encontrado")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<ProductDto> update(@ApiParam(value = "Id do produto", required = true, example = "1") @PathVariable Long id, @RequestBody @Valid ProductFormDto body) {
		return ResponseEntity.ok(this.productService.update(id, body));
	}

	@ApiOperation(value = "Remove um produto pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Produto deletado"),
			@ApiResponse(code = 404, message = "Produto não encontrado")})
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> delete(@ApiParam(value = "Id do produto", required = true, example = "1") @PathVariable Long id) {
		this.productService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
