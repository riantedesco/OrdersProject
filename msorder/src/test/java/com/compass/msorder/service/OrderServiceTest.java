package com.compass.msorder.service;

import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.OrderUpdateDto;
import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.CustomerFixture;
import com.compass.msorder.fixture.OrderFixture;
import com.compass.msorder.fixture.ProductOrderFixture;
import com.compass.msorder.publisher.payment.PaymentPublisher;
import com.compass.msorder.repository.CustomerRepository;
import com.compass.msorder.repository.OrderRepository;
import com.compass.msorder.service.impl.OrderServiceImpl;
import com.compass.msorder.service.impl.ProductOrderServiceImpl;
import com.compass.msorder.util.validation.Validation;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class OrderServiceTest {

    @Mock
    private PaymentPublisher paymentPublisher;

    @Mock
    private ProductOrderServiceImpl productOrderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private ModelMapper mapper;

    @Mock
    private Validation validation;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_WhenSendSaveWithValidCustomer_ExpectedOrder ()  {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerFixture.getCustomerEntity()));
        when(productOrderService.save(ProductOrderFixture.getProductOrderFormDto(), OrderFixture.getOrderEntity())).thenReturn(ProductOrderFixture.getProductOrderDto());
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(OrderFixture.getOrderEntity());
        OrderDto response = orderService.save(OrderFixture.getOrderFormDto());

        verify(orderRepository, times(2)).save(any(OrderEntity.class));
        assertEquals(response.getId(), OrderFixture.getOrderDto().getId());
        assertNotNull(response);
    }

    @Test
    void save_WhenSendSaveWithInvalidCustomer_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () -> orderService.save(OrderFixture.getOrderFormDtoWithInvalidCustomer()));

        assertNotNull(response);
        assertEquals("Customer not found", response.getMessage());
    }

    @Test
    void findByIdNumberAndCpfClient_WhenSendFindByIdNumberAndCpfClientWithExistingIdNumberAndCpfClient_ExpectedOrder ()  {
        when(orderRepository.findByIdNumberAndCpfCustomer(anyLong(), anyLong(), anyString())).thenReturn(Optional.of(OrderFixture.getOrderEntity()));
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(OrderFixture.getOrderEntity().getCustomer()));
        OrderDto response = orderService.findByIdNumberAndCpfClient(OrderFixture.getOrderEntity().getId(), OrderFixture.getOrderEntity().getNumber(), OrderFixture.getOrderEntity().getCustomer().getCpf());

        assertNotNull(response);
    }

    @Test
    void findByIdNumberAndCpfClient_WhenSendFindByIdNumberAndCpfClientWithNonExistingIdNumberOrCpfClient_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> orderService.findByIdNumberAndCpfClient(1L, 999999L, "999.999.999-99"));

        assertNotNull(response);
        assertEquals("Order not found", response.getMessage());
    }

    @Test
    void update_WhenSendUpdateWithExistingIdAndValidCustomer_ExpectedOrder ()  {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(OrderFixture.getOrderEntity()));
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerFixture.getCustomerEntity()));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(OrderFixture.getOrderEntity());
        OrderUpdateDto response = orderService.update(OrderFixture.getOrderUpdateDto().getId(), OrderFixture.getOrderUpdateFormDto());

        assertEquals(OrderFixture.getOrderUpdateDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void update_WhenSendUpdateWithNonExistingId_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> orderService.update(1L, OrderFixture.getOrderUpdateFormDto()));

        assertNotNull(response);
        assertEquals("Order not found", response.getMessage());
    }

    @Test
    void update_WhenSendUpdateWithInvalidCustomer_ExpectedInvalidAttributeException ()  {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(OrderFixture.getOrderEntity()));
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () -> orderService.update(1L, OrderFixture.getOrderUpdateFormDtoWithInvalidCustomer()));

        assertNotNull(response);
        assertEquals("Customer not found", response.getMessage());
    }
}
