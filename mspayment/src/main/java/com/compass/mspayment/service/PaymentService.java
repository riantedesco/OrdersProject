package com.compass.mspayment.service;

import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.domain.dto.form.PaymentFormDto;

public interface PaymentService {

    void save(PaymentFormDto body);

    PaymentDto findByIdOrderAndCpfCustomer(Long idOrder, String cpfCustomer);

}
