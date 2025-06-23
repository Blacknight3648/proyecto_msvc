package com.msvc.productos.controllers;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.msvc.model.entity.ProductoModel;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductoControllerTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllProductosWhenListIsRequested(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/productos", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int productoCount = documentContext.read("$.length()");
        assertThat(productoCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$..idProducto");
        assertThat(ids).containsExactlyInAnyOrder(1,2);

        JSONArray names = documentContext.read("$..nombre");
        assertThat(names).containsExactlyInAnyOrder("Perfume 1", "Perfume 2");
    }

    @Test
    public void shouldReturnAnProductoWhenFindById(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/productos/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idProducto = documentContext.read("$.idProducto");
        assertThat(idProducto).isEqualTo(1);

        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Perfume 1");
    }

    @Test
    public void shouldReturnAnProductoWithUnknownId(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/medicos/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewProducto(){
        ProductoModel productoModel = new ProductoModel(1L, "Perfume 1", 20000, "Aroma a frutas");
        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/productos",productoModel, String.class);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idProducto = documentContext.read("$.idProducto");
        assertThat(idProducto).isEqualTo(3);
    }
}
