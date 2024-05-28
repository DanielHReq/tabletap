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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tabletap.model.ItemDados;
import br.com.tabletap.repository.ItemDadosRepository;

@RestController
@RequestMapping("/carrinho/item")
public class ItemDadosController {

    @Autowired
    private ItemDadosRepository itemDadosRepository;


    // listar todos os itens de todos os carrinhos
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<ItemDados>> mostraItensDados () {
        List<ItemDados> itemDadosList = (List<ItemDados>) itemDadosRepository.findAll();
        return new ResponseEntity<>(itemDadosList, HttpStatus.OK);
    }

    // listar todos os itens de um carrinho

    // procura 1 itemDados pelo seu id e o retorna, se existir
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<List<ItemDados>> buscaItemDadosPeloID (@PathVariable(value = "id") Long id) {
        try {
            Optional<ItemDados> itemDados = itemDadosRepository.findById(id);
            List<ItemDados> itemDadosList = new ArrayList<ItemDados>();

            if (itemDados.isPresent()) itemDadosList.add(itemDados.get());
            else throw new Exception("Item do carrinho não encontrado");
            
            return new ResponseEntity<>(itemDadosList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Item do carrinho não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    // adiciona itemDados ao sistema
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<ItemDados> adicionaItemDados (@RequestBody ItemDados itemDados) {
        ItemDados itemDadosSalvo = itemDadosRepository.save(itemDados);
        return new ResponseEntity<>(itemDadosSalvo, HttpStatus.OK);
    }

    // edita itemDados existente no sistema
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<ItemDados> atualizaItemDados (@RequestBody ItemDados itemDados) {
        ItemDados itemDadosSalvo = itemDadosRepository.save(itemDados);
        return new ResponseEntity<>(itemDadosSalvo, HttpStatus.OK);
    }

    // remove itemDados do sistema
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void removeItemDados (@PathVariable("id") Long id) {
        itemDadosRepository.deleteById(id);
    }
}
