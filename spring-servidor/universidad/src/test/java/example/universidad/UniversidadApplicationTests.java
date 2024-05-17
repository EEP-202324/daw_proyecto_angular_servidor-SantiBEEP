package example.universidad;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UniversidadApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAUniWhenDataIsSaved() {
        ResponseEntity<String> response = 
        restTemplate.getForEntity(
        		"/universidad/99", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(99);
        
        String name = documentContext.read("$.name");
        assertThat(name).isEqualTo("uni1");
    }
    
    @Test
    void shouldNotReturnAUniWithAnUnknownId() {
      ResponseEntity<String> response = restTemplate.getForEntity("/universidad/1000", String.class);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).isBlank();
    }
    
    @Test
    void shouldCreateANewUni() {
       Universidad newUni = new Universidad(null, "uni2");
       ResponseEntity<Void> createResponse = restTemplate.postForEntity("/universidad", newUni, Void.class);
       assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
       
       URI locationOfNewUni = createResponse.getHeaders().getLocation();
       ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewUni, String.class);
       assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
       
       DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
       Number id = documentContext.read("$.id");
       String name = documentContext.read("$.name");

       assertThat(id).isNotNull();
       assertThat(name).isEqualTo("uni2");
    }
}