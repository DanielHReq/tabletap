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

import br.com.tabletap.model.Pedido;
import br.com.tabletap.model.Usuario;
import br.com.tabletap.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;


    // listar todos os pedidos do sistema
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Pedido>> mostraPedidos () {
        List<Pedido> pedidoList = (List<Pedido>) pedidoRepository.findAll();
        return new ResponseEntity<>(pedidoList, HttpStatus.OK);
    }

    // listar todos os pedidos de um usuário
    /*@GetMapping(value = "/usuario/{id}", produces = "application/json")
    public ResponseEntity<List<Pedido>> mostraPedidosUsuario (@RequestBody Usuario usuario) {
        List<Pedido> pedidoList = (List<Pedido>) pedidoRepository.findAllByUser(usuario);
        return new ResponseEntity<>(pedidoList, HttpStatus.OK);
    }*/


    // procura 1 pedido pelo seu id e o retorna, se existir
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<List<Pedido>> buscaPedidoPeloID (@PathVariable(value = "id") Long id) {
        try {
            Optional<Pedido> pedido = pedidoRepository.findById(id);
            List<Pedido> pedidoList = new ArrayList<Pedido>();

            if (pedido.isPresent()) pedidoList.add(pedido.get());
            else throw new Exception("Pedido não encontrado");

            return new ResponseEntity<>(pedidoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Pedido não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    // adiciona pedido ao sistema
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Pedido> adicionaPedido (@RequestBody Pedido pedido) {
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return new ResponseEntity<>(pedidoSalvo, HttpStatus.OK);
    }

    // edita pedido existente no sistema
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Pedido> atualizaPedido (@RequestBody Pedido pedido) {
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return new ResponseEntity<>(pedidoSalvo, HttpStatus.OK);
    }

    // remove pedido do sistema
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void removePedido (@PathVariable("id") Long id) {
        pedidoRepository.deleteById(id);
    }
}
