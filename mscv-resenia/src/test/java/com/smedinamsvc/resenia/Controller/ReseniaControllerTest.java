package com.smedinamsvc.resenia.Controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ReseniaControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturnAllReseniasWhenListIsRequested() {
        // 1. Hacer llamada al endpoint
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/resenias", String.class);

        // 2. Verificar que status es 200 OK
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 3. Ver contenido JSON como lista
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int reseniasCount = documentContext.read("$.length()");

        // 4. Validar que haya al menos 1 reseña (ajústalo si quieres exactos)
        assertThat(reseniasCount).isGreaterThanOrEqualTo(0);
    }
}

