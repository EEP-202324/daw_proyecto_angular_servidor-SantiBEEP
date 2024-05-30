package com.ejemplo.universidad;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	
	@GetMapping
	private ResponseEntity<List<Universidad>> findAll(Pageable pageable) {
	    Page<Universidad> page = universidadRepository.findAll(
	            PageRequest.of(
	                    pageable.getPageNumber(),
	                    pageable.getPageSize(),
	                    pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))
	    ));
	    return ResponseEntity.ok(page.getContent());
	}
	
	@PutMapping("/{requestedId}")
	private ResponseEntity<Void> putCashCard(@PathVariable Long requestedId, @RequestBody Universidad uniUpdate) {
		Optional<Universidad> optional = universidadRepository.findById(requestedId);
        if (optional.isPresent()) {
            Universidad universidad = optional.get();
            Universidad updateUniversidad = new Universidad (
                        universidad.getId(), uniUpdate.getName(), universidad.getCiudad(), universidad.getImage());
            universidadRepository.save(updateUniversidad);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
	}
}
