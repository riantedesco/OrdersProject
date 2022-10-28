package com.compass.msorder.service.impl;

import com.compass.msorder.domain.ProductEntity;
import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.domain.dto.form.ProductFormDto;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.repository.ProductRepository;
import com.compass.msorder.service.ProductService;
import com.compass.msorder.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public ProductDto save(ProductFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		ProductEntity product = mapper.map(body, ProductEntity.class);
		validation.validateProduct(product);
		ProductEntity productResponse = this.productRepository.save(product);
		return mapper.map(productResponse, ProductDto.class);
	}

	@Override
	public List<ProductDto> list() {
		List<ProductDto> products = this.productRepository.findAll().stream().map(p -> mapper.map(p, ProductDto.class))
				.collect(Collectors.toList());
		return products;
	}

	@Override
	public ProductDto find(Long id) {
		Optional<ProductEntity> product = this.productRepository.findById(id);
		if (!product.isPresent()) {
			throw new NotFoundAttributeException("Product not found");
		}
		return mapper.map(product.get(), ProductDto.class);
	}

	@Override
	public ProductDto update(Long id, ProductFormDto body) {
		Optional<ProductEntity> product = this.productRepository.findById(id);
		if (!product.isPresent()) {
			throw new NotFoundAttributeException("Product not found");
		}
		product.get().setName(body.getName());
		product.get().setDescription(body.getDescription());
		product.get().setBrand(body.getBrand());
		product.get().setPrice(body.getPrice());
		product.get().setActive(body.getActive());

		validation.validateProduct(product.get());
		ProductEntity productResponse = this.productRepository.save(product.get());
		return mapper.map(productResponse, ProductDto.class);
	}

	@Override
	public void delete(Long id) {
		Optional<ProductEntity> product = this.productRepository.findById(id);
		if (!product.isPresent()) {
			throw new NotFoundAttributeException("Product not found");
		}
		this.productRepository.deleteById(id);
	}

}
