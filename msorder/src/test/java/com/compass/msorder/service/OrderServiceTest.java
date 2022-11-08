package com.compass.msorder.service;

import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.OrderFixture;
import com.compass.msorder.repository.OrderRepository;
import com.compass.msorder.service.impl.OrderServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

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
        when(orderRepository.save(OrderFixture.getOrderEntity())).thenReturn(OrderFixture.getOrderEntity());
        OrderDto response = orderService.save(OrderFixture.getOrderFormDto());

        assertEquals(OrderFixture.getOrderDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void saveOrder_WhenSendSaveOrderWithInvaliClient_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> orderService.save(OrderFixture.getOrderFormDtoWithInvalidClient()));

        assertNotNull(response);
        assertEquals("Client not found", response.getMessage());

    }

    @Test
    void findOrder_WhenSendFindOrderWithIdValid_ExpectedOrder ()  {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(OrderFixture.getOrderEntity()));
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
        when(orderRepository.save(OrderFixture.getOrderEntity())).thenReturn(OrderFixture.getOrderEntity());
        OrderDto response = orderService.update(OrderFixture.getOrderEntity().getId(), OrderFixture.getOrderUpdateFormDto());

        assertEquals(OrderFixture.getOrderDto().getId(), response.getId());
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
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> orderService.update(1L, OrderFixture.getOrderUpdateFormDtoWithInvalidClient()));

        assertNotNull(response);
        assertEquals("Client not found", response.getMessage());
    }
}
