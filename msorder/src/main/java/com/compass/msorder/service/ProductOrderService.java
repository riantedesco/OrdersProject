package com.compass.msorder.service;

import com.compass.msorder.domain.dto.ProductOrderDto;
import com.compass.msorder.domain.dto.form.ProductOrderFormDto;

public interface ProductOrderService {

    ProductOrderDto save(ProductOrderFormDto body);

}
