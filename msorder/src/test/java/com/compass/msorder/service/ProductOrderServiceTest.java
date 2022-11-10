package com.compass.msorder.service;

import com.compass.msorder.domain.ProductOrderEntity;
import com.compass.msorder.domain.dto.ProductOrderDto;
import com.compass.msorder.exception.InactiveProductException;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.ProductFixture;
import com.compass.msorder.fixture.ProductOrderFixture;
import com.compass.msorder.repository.ProductOrderRepository;
import com.compass.msorder.repository.ProductRepository;
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
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class ProductOrderServiceTest {

    @Mock
    private ProductOrderRepository productOrderRepository;

    @Mock
    private ProductRepository productRepository;

    @Spy
    private ModelMapper mapper;

    @Mock
    private Validation validation;

    @InjectMocks
    private ProductOrderServiceImpl productOrderService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveProductOrder_WhenSendSaveProductOrderValid_ExpectedProductOrder ()  {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductFixture.getProductEntity()));
        when(productOrderRepository.save(any(ProductOrderEntity.class))).thenReturn(ProductOrderFixture.getProductOrderEntity());
        ProductOrderDto response = productOrderService.save(ProductOrderFixture.getProductOrderFormDto(), ProductOrderFixture.getProductOrderEntity().getOrder());

        verify(productOrderRepository, times(1)).save(any(ProductOrderEntity.class));
        assertEquals(response.getId(), ProductOrderFixture.getProductOrderEntity().getId());
        assertNotNull(response);
    }

    @Test
    void saveProductOrder_WhenSendSaveProductOrderWithInvalidProduct_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> productOrderService.save(ProductOrderFixture.getProductOrderFormWithInvalidIdProduct(), ProductOrderFixture.getProductOrderEntity().getOrder()));

        assertNotNull(response);
        assertEquals("Product not found", response.getMessage());
    }

    @Test
    void saveProductOrder_WhenSendSaveProductOrderWithInactiveProduct_ExpectedInactiveProductException ()  {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductFixture.getProductEntityWithInactiveProduct()));
        InactiveProductException response = assertThrows(InactiveProductException.class, () -> productOrderService.save(ProductOrderFixture.getProductOrderFormWithInactiveProduct(), ProductOrderFixture.getProductOrderEntity().getOrder()));

        assertNotNull(response);
        assertEquals("Product inactive", response.getMessage());
    }

}
