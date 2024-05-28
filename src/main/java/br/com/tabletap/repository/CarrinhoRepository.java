package br.com.tabletap.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tabletap.model.Carrinho;

@Repository
public interface CarrinhoRepository extends CrudRepository<Carrinho, Long> {

}
