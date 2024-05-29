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
	        Universidad cashCard = new Universidad(99L, "Universidad Primera", "Madrid", "url");
	        assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
	        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
	        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
	                .isEqualTo(99);
	        assertThat(json.write(cashCard)).hasJsonPathStringValue("@.name");
	        assertThat(json.write(cashCard)).extractingJsonPathStringValue("@.name")
	             .isEqualTo("Universidad Primera");
	        assertThat(json.write(cashCard)).hasJsonPathStringValue("@.ciudad");
	        assertThat(json.write(cashCard)).extractingJsonPathStringValue("@.ciudad")
	             .isEqualTo("Madrid");
	        assertThat(json.write(cashCard)).hasJsonPathStringValue("@.image");
	        assertThat(json.write(cashCard)).extractingJsonPathStringValue("@.image")
	             .isEqualTo("url");
	    }
	    
	    @Test
	    void universidadDeserializationTest() throws IOException {
	       String expected = """
	               {
	                   "id":99,
	                   "name":"Universidad Primera",
	                   "ciudad":"Madrid",
	                   "image":"url"
	               }
	               """;
	       assertThat(json.parse(expected))
	               .isEqualTo(new Universidad(99L, "Universidad Primera", "Madrid", "url"));
	       assertThat(json.parseObject(expected).getId()).isEqualTo(99);
	       assertThat(json.parseObject(expected).getName()).isEqualTo("Universidad Primera");
	       assertThat(json.parseObject(expected).getCiudad()).isEqualTo("Madrid");
	       assertThat(json.parseObject(expected).getImage()).isEqualTo("url");
	    }
	}

