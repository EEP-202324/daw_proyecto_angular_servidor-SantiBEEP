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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

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
    
    @Test
    void shouldReturnAPageOfUnis() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades?page=0&size=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(1);
    }
    
    @Test
    void shouldReturnASortedPageOfUnis() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades?page=0&size=1&sort=id,desc", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray read = documentContext.read("$[*]");
        assertThat(read.size()).isEqualTo(1);

        Number id = documentContext.read("$[0].id");
        assertThat(id).isEqualTo(101);
    }
    
    @Test
    void shouldReturnASortedPageOfUnisWithNoParametersAndUseDefaultValues() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(3);

        JSONArray names = documentContext.read("$..name");
        assertThat(names).containsExactly("Universidad Primera", "Universidad Segunda", "Universidad Tercera");
    }
    
    @Test
    @DirtiesContext
    void shouldUpdateAnExistingUni() {
        Universidad uniUpdate = new Universidad(null, "Nombre Cambiado", null, null);
        HttpEntity<Universidad> request = new HttpEntity<>(uniUpdate);
        ResponseEntity<Void> response = restTemplate
                .exchange("/universidades/99", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        
        ResponseEntity<String> getResponse = restTemplate
                .getForEntity("/universidades/99", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        String name = documentContext.read("$.name");
        String ciudad = documentContext.read("$.ciudad");
        String image = documentContext.read("$.image");
        assertThat(id).isEqualTo(99);
        assertThat(name).isEqualTo("Nombre Cambiado");
        assertThat(ciudad).isEqualTo("Madrid");
        assertThat(image).isEqualTo("url");
    }
    
    @Test
    void shouldNotUpdateAUniThatDoesNotExist() {
        Universidad unknownUni = new Universidad(null, "Nombre Cambiado", null, null);
        HttpEntity<Universidad> request = new HttpEntity<>(unknownUni);
        ResponseEntity<Void> response = restTemplate
                .exchange("/universidades/99999", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}