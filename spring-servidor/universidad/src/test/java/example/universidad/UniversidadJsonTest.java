package example.universidad;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
class UniversidadJsonTest {
	
	@Autowired
    private JacksonTester<Universidad> json;
	
	@Autowired
	private JacksonTester<Universidad[]> jsonList;

	private Universidad[] universidades;
	
	@BeforeEach
	void setUp() {
		universidades = Arrays.array(new Universidad(99L, "uni1", "santi"),
				new Universidad(100L, "uni2", "santi"),
				new Universidad(101L, "uni3", "santi"));
	}
	
	@Test
    void universidadSerializationTest() throws IOException {
        Universidad uni = universidades[0];
        assertThat(json.write(uni)).isStrictlyEqualToJson("single.json");
        assertThat(json.write(uni)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(uni)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(uni)).hasJsonPathStringValue("@.name");
        assertThat(json.write(uni)).extractingJsonPathStringValue("@.name")
             .isEqualTo("uni1");
        assertThat(json.write(uni)).hasJsonPathStringValue("@.owner");
        assertThat(json.write(uni)).extractingJsonPathStringValue("@.owner")
             .isEqualTo("santi");
    }
	
	@Test
	void universidadDeserializationTest() throws IOException {
	   String expected = """
	           [
            { "id": 99, "name": "uni1", "owner": "santi" },
            { "id": 100, "name": "uni2", "owner": "santi" },
            { "id": 101, "name": "uni3", "owner": "santi" }
         ]
	           """;
	   assertThat(jsonList.parse(expected)).isEqualTo(universidades);
	}
}
