package com.msvc.productos.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductoControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturnAllProductosWhenListIsRequested(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/productos", String.class);

    }
}
