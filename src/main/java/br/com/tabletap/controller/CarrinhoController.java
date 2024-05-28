package br.com.tabletap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tabletap.model.Carrinho;
import br.com.tabletap.repository.CarrinhoRepository;

@RestController
@RequestMapping("carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoRepository carrinhoRepository;


    // listar todos os carrinhos do sistema
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Carrinho>> mostraCarrinhos () {
        List<Carrinho> carrinhoList = (List<Carrinho>) carrinhoRepository.findAll();
        return new ResponseEntity<>(carrinhoList, HttpStatus.OK);
    }

    // procura 1 Carrinho pelo seu id e o retorna, se existir
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<List<Carrinho>> buscaCarrinhoPeloID (@PathVariable(value = "id") Long id) {
        try {
            Optional<Carrinho> carrinho = carrinhoRepository.findById(id);
            List<Carrinho> carrinhoList = new ArrayList<Carrinho>();
            
            if (carrinho.isPresent()) carrinhoList.add(carrinho.get());
            else throw new Exception("Carrinho não encontrado");
            
            return new ResponseEntity<>(carrinhoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Carrinho não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    // adiciona Carrinho ao sistema
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Carrinho> adicionaCarrinho (@RequestBody Carrinho carrinho) {
        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinho);
        return new ResponseEntity<>(carrinhoSalvo, HttpStatus.OK);
    }

    // edita Carrinho existente no sistema
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Carrinho> atualizaCarrinho (@RequestBody Carrinho carrinho) {
        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinho);
        return new ResponseEntity<>(carrinhoSalvo, HttpStatus.OK);
    }

    // remove Carrinho do sistema
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void removeCarrinho (@PathVariable("id") Long id) {
        carrinhoRepository.deleteById(id);
    }
}
