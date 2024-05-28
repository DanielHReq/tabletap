package br.com.tabletap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tabletap.model.Notificacao;
import br.com.tabletap.repository.NotificacaoRepository;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    
    // listar todas as notificacoes do sistema
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Notificacao>> mostraNotificacaos () {
        List<Notificacao> notificacaoList = (List<Notificacao>) notificacaoRepository.findAll();
        return new ResponseEntity<>(notificacaoList, HttpStatus.OK);
    }

    // procura 1 notificacao pelo seu id e a retorna, se existir
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<List<Notificacao>> buscaNotificacao (@PathVariable(value = "id") Long id) {
        try {
            Optional<Notificacao> notificacao = notificacaoRepository.findById(id);
            List<Notificacao> notificacaoList = new ArrayList<Notificacao>();
            
            if (notificacao.isPresent()) notificacaoList.add(notificacao.get());
            else throw new Exception("Notificação não encontrada");
            
            return new ResponseEntity<>(notificacaoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Notificação não encontrada", HttpStatus.NOT_FOUND);
        }
    }
    
    // adicionar notificacao ao sistema
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Notificacao> adicionaNotificacao (@RequestBody Notificacao notificacao) {
        Notificacao notificacaoSalva = notificacaoRepository.save(notificacao);
        return new ResponseEntity<>(notificacaoSalva, HttpStatus.OK);
    }

    // remover notificacao do sistema
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void removeNotificacao (@PathVariable("id") Long id) {
        notificacaoRepository.deleteById(id);
    }
}
