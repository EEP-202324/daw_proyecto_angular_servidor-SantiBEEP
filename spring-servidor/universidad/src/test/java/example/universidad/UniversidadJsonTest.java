package example.universidad;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class UniversidadJsonTest {
	
	@Autowired
    private JacksonTester<Universidad> json;
	
	@Test
    void universidadSerializationTest() throws IOException {
        Universidad uni = new Universidad(99L, "uni1");
        assertThat(json.write(uni)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(uni)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(uni)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(uni)).hasJsonPathNumberValue("@.name");
        assertThat(json.write(uni)).extractingJsonPathNumberValue("@.name")
             .isEqualTo("uni1");
    }
	
	@Test
	void universidadDeserializationTest() throws IOException {
	   String expected = """
	           {
	               "id":99,
	               "name":'uni1'
	           }
	           """;
	   assertThat(json.parse(expected))
	           .isEqualTo(new Universidad(99L, "uni1"));
	   assertThat(json.parseObject(expected).id()).isEqualTo(99);
	   assertThat(json.parseObject(expected).name()).isEqualTo("uni1");
	}
}
