package com.compass.mspayment.service;

import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.exception.NotFoundAttributeException;
import com.compass.mspayment.fixture.PaymentFixture;
import com.compass.mspayment.publisher.order.OrderPublisher;
import com.compass.mspayment.repository.PaymentRepository;
import com.compass.mspayment.service.impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class PaymentServiceTest {

    @Mock
    private OrderPublisher orderPublisher;

    @Mock
    private PaymentRepository paymentRepository;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_WhenSendSaveWithTotalOrderLessThan1000_ExpectedStatusOrderEqualsPaymentConfirmed ()  {
        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(PaymentFixture.getPaymentEntity());
        paymentService.save(PaymentFixture.getPaymentFormDtoWithAuthorizedPrice());

        verify(paymentRepository, times(1)).save(any(PaymentEntity.class));
    }

    @Test
    void save_WhenSendSaveWithTotalOrderGreaterThanOrEqualsTo1000_ExpectedStatusOrderEqualsPaymentUnauthorized ()  {
        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(PaymentFixture.getPaymentEntity());
        paymentService.save(PaymentFixture.getPaymentFormDtoWithUnauthorizedPrice());

        verify(paymentRepository, times(1)).save(any(PaymentEntity.class));
    }

    @Test
    void find_WhenSendFindByIdOrderAndCpfCustomerWithExistingIdOrderAndCpfCustomer_ExpectedClient ()  {
        when(paymentRepository.findByIdOrderAndCpfCustomer(anyLong(), anyString())).thenReturn(Optional.of(PaymentFixture.getPaymentEntity()));
        PaymentDto response = paymentService.findByIdOrderAndCpfCustomer(PaymentFixture.getPaymentEntity().getIdOrder(), PaymentFixture.getPaymentEntity().getCpfCustomer());

        assertNotNull(response);
    }

    @Test
    void find_WhenSendFindByIdOrderAndCpfCustomerWithNonExistingIdOrderOrCpfCustomer_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> paymentService.findByIdOrderAndCpfCustomer(5000L,  "999.999.999-99"));

        assertNotNull(response);
        assertEquals("Payment not found", response.getMessage());
    }
}
