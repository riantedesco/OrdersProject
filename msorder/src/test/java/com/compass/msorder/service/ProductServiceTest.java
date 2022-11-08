package com.compass.msorder.service;

import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.ProductFixture;
import com.compass.msorder.repository.ProductRepository;
import com.compass.msorder.service.impl.ProductServiceImpl;
import com.compass.msorder.util.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Spy
    private ModelMapper mapper;

    @Mock
    private Validation validation;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveProduct_WhenSendSaveProductValid_ExpectedProduct ()  {
        when(productRepository.save(ProductFixture.getProductEntity())).thenReturn(ProductFixture.getProductEntity());
        ProductDto response = productService.save(ProductFixture.getProductFormDto());

        assertEquals(ProductFixture.getProductDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void listProduct_WhenSendListProducts_ExpectedProducts ()  {
        when(productRepository.findAll()).thenReturn(List.of(ProductFixture.getProductEntity()));
        List<ProductDto> response = productService.list();

        assertFalse(response.isEmpty());
    }

    @Test
    void findProduct_WhenSendFindProductWithIdValid_ExpectedProduct ()  {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductFixture.getProductEntity()));
        ProductDto response = productService.find(ProductFixture.getProductEntity().getId());

        assertNotNull(response);
    }

    @Test
    void findProduct_WhenSendFindProductWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> productService.find(1L));

        assertNotNull(response);
        assertEquals("Product not found", response.getMessage());
    }

    @Test
    void updateProduct_WhenSendUpdateProductWithIdValid_ExpectedProduct ()  {
        when(productRepository.save(ProductFixture.getProductEntity())).thenReturn(ProductFixture.getProductEntity());
        ProductDto response = productService.update(ProductFixture.getProductEntity().getId(), ProductFixture.getProductFormDto());

        assertEquals(ProductFixture.getProductDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void updateProduct_WhenSendUpdateProductWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> productService.update(1L, ProductFixture.getProductFormDto()));

        assertNotNull(response);
        assertEquals("Product not found", response.getMessage());
    }

    @Test
    void deleteProduct_WhenSendDeleteProductWithIdValid_ExpectedOk ()  {
        when(productRepository.save(ProductFixture.getProductEntity())).thenReturn(ProductFixture.getProductEntity());
        ProductDto response = productService.save(ProductFixture.getProductFormDto());

        doNothing().when(productRepository).deleteById(response.getId());
        productService.delete(response.getId());

        Mockito.verify(productRepository, times(1)).deleteById(response.getId());
    }

    @Test
    void deleteProduct_WhenSendDeleteProductWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> productService.delete(1L));

        assertNotNull(response);
        assertEquals("Product not found", response.getMessage());
    }
}
