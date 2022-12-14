package com.compass.msorder.service;

import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.OrderUpdateDto;
import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.domain.dto.form.OrderUpdateFormDto;

public interface OrderService {

    OrderDto save(OrderFormDto body);

    OrderDto findByIdNumberAndCpfClient(Long id, Long number, String cpfClient);

    OrderUpdateDto update(Long id, OrderUpdateFormDto body);

}
