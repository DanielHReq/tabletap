
package br.com.tabletap.controller;

import java.util.List;
import java.util.Optional;
//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tabletap.model.Usuario;
import br.com.tabletap.repository.UsuarioRepository;

//@Controller("IndexController")
@RestController
@RequestMapping("/usuario")
public class IndexController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    // microsserviço 1 - template
    @GetMapping(value = "/endpoint1", produces = "application/json")
    public ResponseEntity init (@RequestParam(value = "param1", defaultValue = "default") String param1) {
        return new ResponseEntity("Hello World: "+param1, HttpStatus.OK);
    }


    // microsserviço 2 - lista todos os usuários
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Usuario>> usuario () {
        List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }


    // mostra 1 user, pelo seu id
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Usuario> init4 (@PathVariable(value = "id") Long id) {

        try {
            Optional<Usuario> user = usuarioRepository.findById(id);
            return new ResponseEntity(user.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // ... cadastrar
    @PostMapping(value="/", produces = "application/json")
    public ResponseEntity<Usuario> cadastrar (@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new ResponseEntity(usuarioSalvo, HttpStatus.OK);
    }


    // ... atualizar
    @PutMapping(value="/", produces = "application/json")
    public ResponseEntity<Usuario> atualizar (@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new ResponseEntity(usuarioSalvo, HttpStatus.OK);
    }

    // ... deletar
    @DeleteMapping(value="/{id}", produces = "application/text")
    public String deletar(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
        return "ok";
    }
}
