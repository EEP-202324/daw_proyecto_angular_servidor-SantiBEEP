package com.ejemplo.universidad;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;
import net.minidev.json.JSONArray;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UniversidadApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAUniWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades/99", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(99);
        
        String name = documentContext.read("$.name");
        assertThat(name).isEqualTo("Universidad Primera");

        String ciudad = documentContext.read("$.ciudad");
        assertThat(ciudad).isEqualTo("Madrid");
        
        String image = documentContext.read("$.image");
        assertThat(image).isEqualTo("url");
    }
    
    @Test
    void shouldNotReturnAUniWithAnUnknownId() {
      ResponseEntity<String> response = restTemplate.getForEntity("/universidades/1000", String.class);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).isBlank();
    }
    
    @Test
    @DirtiesContext
    void shouldCreateANewUni() {
       Universidad newUni = new Universidad(null, "Universidad Segunda", "Madrid", "url");
       ResponseEntity<Void> createResponse = restTemplate.postForEntity("/universidades", newUni, Void.class);
       assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
       
       URI locationOfNewUni = createResponse.getHeaders().getLocation();
       ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewUni, String.class);
       assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
       
       DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
       Number id = documentContext.read("$.id");
       String name = documentContext.read("$.name");
       String ciudad = documentContext.read("$.ciudad");
       String image = documentContext.read("$.image");

       assertThat(id).isNotNull();
       assertThat(name).isEqualTo("Universidad Segunda");
       assertThat(ciudad).isEqualTo("Madrid");
       assertThat(image).isEqualTo("url");
    }
    
    @Test
    void shouldReturnAllUnisWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int cashCardCount = documentContext.read("$.length()");
        assertThat(cashCardCount).isEqualTo(3);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(99, 100, 101);

        JSONArray names = documentContext.read("$..name");
        assertThat(names).containsExactlyInAnyOrder("Universidad Primera", "Universidad Segunda", "Universidad Tercera");
        
        JSONArray images = documentContext.read("$..image");
        assertThat(images).containsExactlyInAnyOrder("url", "url", "url");
        
        JSONArray ciudades = documentContext.read("$..ciudad");
        assertThat(ciudades).containsExactlyInAnyOrder("Madrid", "Madrid", "Madrid");
    }
}