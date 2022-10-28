package com.compass.msorder.service;

import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.OrderUpdateFormDtoFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void shouldNotFind ()  {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.orderService.findByIdNumberAndCpfClient(5000L, 5000L, "111.111.111-11");
        });
        Assertions.assertTrue(exception.getMessage().contains("Order not found"));
    }

    @Test
    void shouldNotUpdate () {
        Exception exception = Assertions.assertThrows(NotFoundAttributeException.class, () -> {
            this.orderService.update(5000L, OrderUpdateFormDtoFixture.getDefault());
        });
        Assertions.assertTrue(exception.getMessage().contains("Order not found"));
    }

}
