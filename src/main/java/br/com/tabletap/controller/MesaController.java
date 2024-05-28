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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tabletap.model.Mesa;
import br.com.tabletap.model.MesaDTO;
import br.com.tabletap.repository.MesaRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    
    // listar todas as mesas do sistema
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Mesa>> mostraMesas () {
        List<Mesa> mesaList = (List<Mesa>) mesaRepository.findAll();
        return new ResponseEntity<>(mesaList, HttpStatus.OK);
    }

    // procura 1 mesa pelo seu id e a retorna, se existir
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<List<Mesa>> buscaMesa (@PathVariable(value = "id") Long id) {
        try {
            Optional<Mesa> mesa = mesaRepository.findById(id);
            List<Mesa> mesaList = new ArrayList<Mesa>();
            
            if (mesa.isPresent()) mesaList.add(mesa.get());
            else throw new Exception("Mesa não encontrada");
            
            return new ResponseEntity<>(mesaList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Mesa não encontrada", HttpStatus.NOT_FOUND);
        }
    }
    
    // adicionar mesa ao sistema
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Mesa> adicionaMesa (@RequestBody @Valid MesaDTO mesaDTO) {
        Mesa mesa = new Mesa(mesaDTO.id(), mesaDTO.valor());
        mesa = mesaRepository.save(mesa);
        return new ResponseEntity<>(mesa, HttpStatus.OK);
    }

    // remover mesa do sistema
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void removeMesa (@PathVariable("id") Long id) {
        mesaRepository.deleteById(id);
    }
}
