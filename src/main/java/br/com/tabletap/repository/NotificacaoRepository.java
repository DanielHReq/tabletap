package br.com.tabletap.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tabletap.model.Notificacao;

@Repository
public interface NotificacaoRepository extends CrudRepository<Notificacao, Long> {

}
