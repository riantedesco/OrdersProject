package com.compass.msorder.service;

import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.domain.dto.form.ProductFormDto;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductFormDto body);

    List<ProductDto> list();

    ProductDto find(Long id);

    ProductDto update(Long id, ProductFormDto body);

    void delete(Long id);

}
