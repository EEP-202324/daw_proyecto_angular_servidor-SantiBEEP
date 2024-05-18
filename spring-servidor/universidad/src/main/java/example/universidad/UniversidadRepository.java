package example.universidad;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UniversidadRepository extends CrudRepository<Universidad, Long>, PagingAndSortingRepository<Universidad, Long> {
	Universidad findByIdAndOwner(Long id, String owner);
	Page<Universidad> findByOwner(String owner, PageRequest pageRequest);
}
