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

import br.com.tabletap.model.Comanda;
import br.com.tabletap.repository.ComandaRepository;

@RestController
@RequestMapping("/comanda")
public class ComandaController {

    @Autowired
    private ComandaRepository comandaRepository;


    // listar todos as comandas do sistema
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Comanda>> mostraComandas () {
        List<Comanda> comandaList = (List<Comanda>) comandaRepository.findAll();
        return new ResponseEntity<>(comandaList, HttpStatus.OK);
    }

    // procura 1 comanda pelo seu id e o retorna, se existir
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<List<Comanda>> buscaComandaPeloID (@PathVariable(value = "id") Long id) {
        try {
            Optional<Comanda> comanda = comandaRepository.findById(id);
            List<Comanda> comandaList = new ArrayList<Comanda>();

            if (comanda.isPresent()) comandaList.add(comanda.get());
            else throw new Exception("Comanda não encontrada");

            return new ResponseEntity<>(comandaList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Comanda não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    // adiciona comanda ao sistema
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Comanda> adicionaComanda (@RequestBody Comanda comanda) {
        Comanda comandaSalva = comandaRepository.save(comanda);
        return new ResponseEntity<>(comandaSalva, HttpStatus.OK);
    }

    // edita comanda existente no sistema
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Comanda> atualizaComanda (@RequestBody Comanda comanda) {
        Comanda comandaSalva = comandaRepository.save(comanda);
        return new ResponseEntity<>(comandaSalva, HttpStatus.OK);
    }

    // remove comanda do sistema
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void removeComanda (@PathVariable("id") Long id) {
        comandaRepository.deleteById(id);
    }
}
