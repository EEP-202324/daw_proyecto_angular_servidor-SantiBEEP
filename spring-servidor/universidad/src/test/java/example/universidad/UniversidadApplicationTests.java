package example.universidad;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
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
    void shouldReturnAllUnisWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidad", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
       DocumentContext documentContext = JsonPath.parse(response.getBody());

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(99, 100, 101);

        JSONArray names = documentContext.read("$..name");
        assertThat(names).containsExactlyInAnyOrder("uni1", "uni2", "uni3");
        
        int uniCount = documentContext.read("$.length()");
        assertThat(uniCount).isEqualTo(3);
    }
    
    @Test
    void shouldNotReturnAUniWithAnUnknownId() {
      ResponseEntity<String> response = restTemplate.getForEntity("/universidad/1000", String.class);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).isBlank();
    }
    
    @Test
    @DirtiesContext
    void shouldCreateANewUni() {
       Universidad newUni = new Universidad(null, "uni2", "santi");
       ResponseEntity<Void> createResponse = restTemplate.postForEntity("/universidad", newUni, Void.class);
       assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
       
       URI locationOfNewUni = createResponse.getHeaders().getLocation();
       ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewUni, String.class);
       assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
       
       DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
       Number id = documentContext.read("$.id");
       String name = documentContext.read("$.name");
       String owner = documentContext.read("$.owner");

       assertThat(id).isNotNull();
       assertThat(name).isEqualTo("uni2");
       assertThat(owner).isEqualTo("santi");
    }
    
    @Test
    void shouldReturnAPageOfUnis() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidad?page=0&size=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(1);
    }
    
    @Test
    void shouldReturnASortedPageOfUnis() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidad?page=0&size=1&sort=name,desc", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray read = documentContext.read("$[*]");
        assertThat(read.size()).isEqualTo(1);

        String name = documentContext.read("$[0].name");
        assertThat(name).isEqualTo("uni3");
    }
    
    @Test
    void shouldReturnASortedPageOfUnisWithNoParametersAndUseDefaultValues() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidad", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(3);

        JSONArray names = documentContext.read("$..name");
        assertThat(names).containsExactly("uni1", "uni2", "uni3");
    }
    
    
}