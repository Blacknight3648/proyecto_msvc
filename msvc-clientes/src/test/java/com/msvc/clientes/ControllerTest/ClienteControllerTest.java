package com.msvc.clientes.ControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void shouldRetunrAllClienteWhenListIsRequested(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/clientes",String.class);
        //assertThat();

    }
}
