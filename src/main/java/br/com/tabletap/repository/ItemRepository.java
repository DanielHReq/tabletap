package br.com.tabletap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tabletap.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    /*@Query("select i from Item i where i.nome = ?1 limit 1")
    //Optional<Item> findByName(String nome);
    Item findByName(String nome);*/
}
