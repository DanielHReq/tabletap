package br.com.tabletap.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tabletap.model.ItemDados;

@Repository
public interface ItemDadosRepository extends CrudRepository<ItemDados, Long> {

}
