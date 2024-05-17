package example.universidad;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/universidad")
class UniController {
	@GetMapping("/{requestedId}")
	private ResponseEntity<Universidad> findById(@PathVariable Long requestedId) {
	   if (requestedId.equals(99L)) {
	        Universidad uni = new Universidad(99L, "uni1");
	        return ResponseEntity.ok(uni);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	}