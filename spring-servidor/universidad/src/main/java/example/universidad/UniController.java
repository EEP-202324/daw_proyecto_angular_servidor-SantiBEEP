package example.universidad;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/universidad")
class UniController {
	private final UniversidadRepository universidadRepository;

	private UniController(UniversidadRepository universidadRepository) {
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
	private ResponseEntity<Void> createUni(@RequestBody Universidad newUniRequest, UriComponentsBuilder ucb) {
		Universidad savedUni = universidadRepository.save(newUniRequest);
		URI locationOfNewUni = ucb.path("universidad/{id}").buildAndExpand(savedUni.id()).toUri();
		return ResponseEntity.created(locationOfNewUni).build();
	}
	
	@GetMapping
	private ResponseEntity<List<Universidad>> findAll(Pageable pageable) {
	    Page<Universidad> page = universidadRepository.findAll(
	    		PageRequest.of(
	    			     pageable.getPageNumber(),
	    			     pageable.getPageSize(),
	    			     pageable.getSortOr(Sort.by(Sort.Direction.ASC, "name"))
	    			));
	    return ResponseEntity.ok(page.getContent());
	}
	
	
}