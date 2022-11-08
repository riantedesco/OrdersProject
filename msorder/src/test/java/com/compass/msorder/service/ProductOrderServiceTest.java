package com.compass.msorder.service;

import com.compass.msorder.domain.dto.ProductDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        when(productRepository.save(ProductFixture.getProductEntity())).thenReturn(ProductFixture.getProductEntity());

        when(productOrderRepository.save(ProductOrderFixture.getProductOrderEntity())).thenReturn(ProductOrderFixture.getProductOrderEntity());
        ProductOrderDto response = productOrderService.save(ProductOrderFixture.getProductOrderFormDto());

        assertEquals(ProductOrderFixture.getProductOrderDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void saveProductOrder_WhenSendSaveProductOrderWithInvalidProduct_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> productOrderService.save(ProductOrderFixture.getProductOrderFormWithInvalidIdProduct()));

        assertNotNull(response);
        assertEquals("Product not found", response.getMessage());

    }

    @Test
    void saveProductOrder_WhenSendSaveProductOrderWithInactiveProduct_ExpectedInactiveProductException ()  {
        InactiveProductException response = assertThrows(InactiveProductException.class, () -> productOrderService.save(ProductOrderFixture.getProductOrderFormWithInactiveProduct()));

        assertNotNull(response);
        assertEquals("Product inactive", response.getMessage());

    }

}
