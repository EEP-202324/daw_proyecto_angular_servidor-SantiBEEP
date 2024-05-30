package com.ejemplo.universidad;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import java.net.URI;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/universidades")
class UniversidadController {

	private final UniversidadRepository universidadRepository;

	private UniversidadController(UniversidadRepository universidadRepository) {
		this.universidadRepository = universidadRepository;
	}

	@GetMapping("/{requestedId}")
	private ResponseEntity<Universidad> findById(@PathVariable Long requestedId) {
		Optional<Universidad> uniOptional = universidadRepository.findById(requestedId);
		if (uniOptional.isPresent()) {
			return ResponseEntity.ok(uniOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	private ResponseEntity<Void> createCashCard(@RequestBody Universidad newUniRequest, UriComponentsBuilder ucb) {
	   Universidad savedCashCard = universidadRepository.save(newUniRequest);
	   URI locationOfNewUni = ucb
	            .path("universidades/{id}")
	            .buildAndExpand(savedCashCard.getId())
	            .toUri();
	   return ResponseEntity.created(locationOfNewUni).build();
	}
	
	@GetMapping()
	private ResponseEntity<Iterable<Universidad>> findAll() {
	   return ResponseEntity.ok(universidadRepository.findAll());
	}
}
