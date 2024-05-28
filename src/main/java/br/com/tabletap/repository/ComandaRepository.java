package br.com.tabletap.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tabletap.model.Comanda;

@Repository
public interface ComandaRepository extends CrudRepository<Comanda, Long> {

}
