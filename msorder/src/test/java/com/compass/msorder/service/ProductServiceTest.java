package com.compass.msorder.service;

import com.compass.msorder.domain.ProductEntity;
import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.fixture.ProductFixture;
import com.compass.msorder.repository.ProductRepository;
import com.compass.msorder.service.impl.ProductServiceImpl;
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
    void save_WhenSendSave_ExpectedProduct ()  {
        when(productRepository.save(any(ProductEntity.class))).thenReturn(ProductFixture.getProductEntity());
        ProductDto response = productService.save(ProductFixture.getProductFormDto());

        verify(productRepository, times(1)).save(any(ProductEntity.class));
        assertEquals(ProductFixture.getProductDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void list_WhenSendList_ExpectedProducts ()  {
        when(productRepository.findAll()).thenReturn(List.of(ProductFixture.getProductEntity()));
        List<ProductDto> response = productService.list();

        assertFalse(response.isEmpty());
    }

    @Test
    void find_WhenSendFindWithExistingId_ExpectedProduct ()  {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductFixture.getProductEntity()));
        ProductDto response = productService.find(ProductFixture.getProductEntity().getId());

        assertNotNull(response);
    }

    @Test
    void find_WhenSendFindWithNonExistingId_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> productService.find(1L));

        assertNotNull(response);
        assertEquals("Product not found", response.getMessage());
    }

    @Test
    void update_WhenSendUpdateWithExistingId_ExpectedProduct ()  {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductFixture.getProductEntity()));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(ProductFixture.getProductEntity());
        ProductDto response = productService.update(ProductFixture.getProductEntity().getId(), ProductFixture.getProductFormDto());

        assertEquals(ProductFixture.getProductDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void update_WhenSendUpdateWithNonExistingId_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> productService.update(1L, ProductFixture.getProductFormDto()));

        assertNotNull(response);
        assertEquals("Product not found", response.getMessage());
    }

    @Test
    void delete_WhenSendDeleteWithExistingId_ExpectedOk ()  {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductFixture.getProductEntity()));
        doNothing().when(productRepository).deleteById(anyLong());
        productService.delete(ProductFixture.getProductEntity().getId());

        verify(productRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void delete_WhenSendDeleteWithNonExistingId_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> productService.delete(1L));

        assertNotNull(response);
        assertEquals("Product not found", response.getMessage());
    }
}
