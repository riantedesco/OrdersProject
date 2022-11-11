package com.compass.msorder.service.impl;

import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.domain.ProductEntity;
import com.compass.msorder.domain.ProductOrderEntity;
import com.compass.msorder.domain.dto.ProductOrderDto;
import com.compass.msorder.domain.dto.form.ProductOrderFormDto;
import com.compass.msorder.exception.InactiveProductException;
import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.repository.ProductOrderRepository;
import com.compass.msorder.repository.ProductRepository;
import com.compass.msorder.service.ProductOrderService;
import com.compass.msorder.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductOrderRepository productOrderRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public ProductOrderDto save(ProductOrderFormDto body, OrderEntity order) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		ProductOrderEntity productOrder = mapper.map(body, ProductOrderEntity.class);

		if (body.getIdProduct() != null) {
			Optional<ProductEntity> product = this.productRepository.findById(body.getIdProduct());
			if (!product.isPresent()) {
				throw new InvalidAttributeException("Product not found");
			}
			if (!product.get().getActive()) {
				throw new InactiveProductException("Product inactive");
			}
			productOrder.setProduct(product.get());
			productOrder.setOrder(order);
			productOrder.setTotal(body.getQuantity() * product.get().getPrice());
		}

		validation.validateProductOrder(productOrder);
		ProductOrderEntity productOrderResponse = this.productOrderRepository.save(productOrder);
		return mapper.map(productOrderResponse, ProductOrderDto.class);
	}

}
