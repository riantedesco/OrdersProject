package com.compass.mspayment.service;

import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.listener.order.dto.OrderListenerDto;

public interface PaymentService {

    void save(OrderListenerDto body);

    PaymentDto findByIdOrderAndCpfClient(Long idOrder, String cpfClient);

}
