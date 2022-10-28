package com.compass.msorder.controller;

import com.compass.msorder.domain.ClientEntity;
import com.compass.msorder.domain.OrderEntity;
import com.compass.msorder.domain.ProductEntity;
import com.compass.msorder.domain.ProductOrderEntity;
import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.domain.dto.form.OrderUpdateFormDto;
import com.compass.msorder.fixture.OrderFormDtoFixture;
import com.compass.msorder.fixture.OrderUpdateFormDtoFixture;
import com.compass.msorder.repository.ClientRepository;
import com.compass.msorder.repository.OrderRepository;
import com.compass.msorder.repository.ProductOrderRepository;
import com.compass.msorder.repository.ProductRepository;
import com.compass.msorder.util.constants.StatusOrderOption;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    ClientEntity client;

    ProductEntity product;

    OrderEntity order;

    ProductOrderEntity productOrder;

    @BeforeAll
    public void setup() {
        this.client = new ClientEntity();
        client.setCpf("000.000.000-00");
        client.setName("Client test");
        client.setSex("Masculino");
        client.setBirthdate(LocalDate.parse("2002-03-30"));
        client.setEmail("test@email.com");
        client.setPhone("(00)00000-0000");

        this.product = new ProductEntity();
        product.setName("Product test");
        product.setDescription("Description test");
        product.setBrand("Brand test");
        product.setPrice(300.00);
        product.setActive(true);

        this.productOrder = new ProductOrderEntity();
        productOrder.setQuantity(1);
        productOrder.setProduct(product);

        this.order = new OrderEntity();
        order.setNumber(2222L);
        order.setDateTime(LocalDateTime.now());
        order.setStatus(StatusOrderOption.ORDER_CREATED);
        order.setClient(client);
        order.setProductOrders(List.of(productOrder));

    }

    @Test
    public void saveOrder_WhenSendMethodPost_ExpectedStatusOk() {
        OrderFormDto orderFormDto = OrderFormDtoFixture.getDefault();

        HttpEntity<OrderFormDto> httpEntity = new HttpEntity<>(orderFormDto);

//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//        this.testRestTemplate.getRestTemplate().setMessageConverters(messageConverters);

        ResponseEntity<OrderDto> response = this.testRestTemplate
                .exchange("/v1/order", HttpMethod.POST, httpEntity, OrderDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getNumber(), 1111L);
    }

    @Test
    public void findOrder_WhenSendMethodGetByIdAndNumberAndCpfClient_ExpectedStatusOk() {
        this.clientRepository.save(this.client);
        this.productRepository.save(this.product);
        this.productOrderRepository.save(this.productOrder);
        OrderEntity order = this.orderRepository.save(this.order);

        ResponseEntity<OrderDto> response = this.testRestTemplate
                .exchange("/v1/order/find?id=" + order.getId() + "&number=" + order.getNumber() + "&cpfClient=" + order.getClient().getCpf(),
                        HttpMethod.GET, null, OrderDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getNumber(), 2222L);
    }

    @Test
    public void updateOrder_WhenSendMethodUpdateById_ExpectedStatusOk() {
        this.clientRepository.save(this.client);
        this.productRepository.save(this.product);
        this.productOrderRepository.save(this.productOrder);
        OrderEntity order = this.orderRepository.save(this.order);

        OrderUpdateFormDto orderUpdateFormDto = OrderUpdateFormDtoFixture.getDefault();
        orderUpdateFormDto.setNumber(3333L);

        HttpEntity<OrderUpdateFormDto> httpEntity = new HttpEntity<>(orderUpdateFormDto);

        ResponseEntity<OrderDto> response = this.testRestTemplate
                .exchange("/v1/order/" + order.getId(), HttpMethod.PUT, httpEntity, OrderDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getNumber(), 3333L);
    }
}