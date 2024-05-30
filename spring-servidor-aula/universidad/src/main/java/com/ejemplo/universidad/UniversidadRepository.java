package com.ejemplo.universidad;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

interface UniversidadRepository extends CrudRepository<Universidad, Long>, PagingAndSortingRepository<Universidad, Long>{

}
