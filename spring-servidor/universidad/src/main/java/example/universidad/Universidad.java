package example.universidad;

import org.springframework.data.annotation.Id;

record Universidad(@Id Long id, String name) {

}