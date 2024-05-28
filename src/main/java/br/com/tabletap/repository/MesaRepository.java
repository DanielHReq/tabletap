package br.com.tabletap.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tabletap.model.Mesa;

@Repository
public interface MesaRepository extends CrudRepository<Mesa, Long> {

}
