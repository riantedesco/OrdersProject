package com.compass.msorder.controller;

import com.compass.msorder.domain.ProductEntity;
import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.domain.dto.form.ProductFormDto;
import com.compass.msorder.fixture.ProductFormDtoFixture;
import com.compass.msorder.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ProductRepository productRepository;

    ProductEntity product;

    @BeforeAll
    public void setup() {
        this.product = new ProductEntity();
        product.setName("Product test");
        product.setDescription("Description test");
        product.setBrand("Brand test");
        product.setPrice(300.00);
        product.setActive(true);
    }

    @Test
    public void saveProduct_WhenSendMethodPost_ExpectedStatusOk() {

        ProductFormDto productFormDto = ProductFormDtoFixture.getDefault();

        HttpEntity<ProductFormDto> httpEntity = new HttpEntity<>(productFormDto);

        ResponseEntity<ProductDto> response = this.testRestTemplate
                .exchange("/v1/product", HttpMethod.POST, httpEntity, ProductDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getName(), "Product default");
    }

    @Test
    public void listProducts_WhenSendMethodGet_ExpectedStatusOk() {

        ResponseEntity<ProductDto[]> response = this.testRestTemplate
                .exchange("/v1/product", HttpMethod.GET, null, ProductDto[].class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void findProduct_WhenSendMethodGetById_ExpectedStatusOk() {
        ProductEntity product = this.productRepository.save(this.product);

        ResponseEntity<ProductDto> response = this.testRestTemplate
                .exchange("/v1/product/" + product.getId(), HttpMethod.GET, null, ProductDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getName(), "Product test");
    }

    @Test
    public void updateProduct_WhenSendMethodUpdateById_ExpectedStatusOk() {

        ProductEntity product = this.productRepository.save(this.product);

        ProductFormDto productFormDto = ProductFormDtoFixture.getDefault();
        productFormDto.setName("Product updated");

        HttpEntity<ProductFormDto> httpEntity = new HttpEntity<>(productFormDto);

        ResponseEntity<ProductDto> response = this.testRestTemplate
                .exchange("/v1/product/" + product.getId(), HttpMethod.PUT, httpEntity, ProductDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getName(), "Product updated");
    }

    @Test
    public void deleteProduct_WhenSendMethodDeleteById_ExpectedStatusOk() {

        ProductEntity product = this.productRepository.save(this.product);

        ResponseEntity<Void> response = this.testRestTemplate
                .exchange("/v1/product/" + product.getId(), HttpMethod.DELETE, null, Void.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}