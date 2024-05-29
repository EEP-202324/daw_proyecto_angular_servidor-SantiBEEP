package com.ejemplo.universidad;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
	@JsonTest
	class UniJsonTest {

	    @Autowired
	    private JacksonTester<Universidad> json;

	    @Test
	    void uniSerializationTest() throws IOException {
	        Universidad cashCard = new Universidad(99L, "Universidad Primera");
	        assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
	        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
	        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
	                .isEqualTo(99);
	        assertThat(json.write(cashCard)).hasJsonPathStringValue("@.name");
	        assertThat(json.write(cashCard)).extractingJsonPathStringValue("@.name")
	             .isEqualTo("Universidad Primera");
	    }
	    
	    @Test
	    void universidadDeserializationTest() throws IOException {
	       String expected = """
	               {
	                   "id":99,
	                   "name":"Universidad Primera"
	               }
	               """;
	       assertThat(json.parse(expected))
	               .isEqualTo(new Universidad(99L, "Universidad Primera"));
	       assertThat(json.parseObject(expected).id()).isEqualTo(99);
	       assertThat(json.parseObject(expected).name()).isEqualTo("Universidad Primera");
	    }
	}

