package com.compass.msorder.service;

import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.OrderUpdateDto;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.ClientFixture;
import com.compass.msorder.fixture.OrderFixture;
import com.compass.msorder.fixture.ProductOrderFixture;
import com.compass.msorder.publisher.payment.PaymentPublisher;
import com.compass.msorder.repository.ClientRepository;
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
    private ClientRepository clientRepository;

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
    void saveOrder_WhenSendSaveOrderValid_ExpectedOrder ()  {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(ClientFixture.getClientEntity()));
        when(productOrderService.save(ProductOrderFixture.getProductOrderFormDto(), OrderFixture.getOrderEntity())).thenReturn(ProductOrderFixture.getProductOrderDto());
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(OrderFixture.getOrderEntity());
        OrderDto response = orderService.save(OrderFixture.getOrderFormDto());

        verify(orderRepository, times(2)).save(any(OrderEntity.class));
        assertEquals(response.getId(), OrderFixture.getOrderDto().getId());
        assertNotNull(response);
    }

    @Test
    void saveOrder_WhenSendSaveOrderWithInvaliClient_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> orderService.save(OrderFixture.getOrderFormDtoWithInvalidClient()));

        assertNotNull(response);
        assertEquals("Client not found", response.getMessage());
    }

    @Test
    void findOrder_WhenSendFindOrderWithIdOrNumberOrCpfClientValid_ExpectedOrder ()  {
        when(orderRepository.findByIdNumberAndCpfClient(anyLong(), anyLong(), anyString())).thenReturn(Optional.of(OrderFixture.getOrderEntity()));
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(OrderFixture.getOrderEntity().getClient()));
        OrderDto response = orderService.findByIdNumberAndCpfClient(OrderFixture.getOrderEntity().getId(), OrderFixture.getOrderEntity().getNumber(), OrderFixture.getOrderEntity().getClient().getCpf());

        assertNotNull(response);
    }

    @Test
    void findOrder_WhenSendFindOrderWithIdOrNumberOrCpfClientInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> orderService.findByIdNumberAndCpfClient(1L, 999999L, "999.999.999-99"));

        assertNotNull(response);
        assertEquals("Order not found", response.getMessage());
    }

    @Test
    void updateOrder_WhenSendUpdateOrderWithIdValid_ExpectedOrder ()  {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(OrderFixture.getOrderEntity()));
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(ClientFixture.getClientEntity()));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(OrderFixture.getOrderEntity());
        OrderUpdateDto response = orderService.update(OrderFixture.getOrderUpdateDto().getId(), OrderFixture.getOrderUpdateFormDto());

        assertEquals(OrderFixture.getOrderUpdateDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void updateOrder_WhenSendUpdateOrderWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> orderService.update(1L, OrderFixture.getOrderUpdateFormDto()));

        assertNotNull(response);
        assertEquals("Order not found", response.getMessage());
    }

    @Test
    void updateOrder_WhenSendUpdateOrderWithClientInvalid_ExpectedNotFoundAttributeException ()  {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(OrderFixture.getOrderEntity()));
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> orderService.update(1L, OrderFixture.getOrderUpdateFormDtoWithInvalidClient()));

        assertNotNull(response);
        assertEquals("Client not found", response.getMessage());
    }
}
