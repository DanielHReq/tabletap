package br.com.tabletap.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Comanda implements Serializable {

    // Construtores

    public Comanda (Cliente cliente) {
        this.cliente = cliente;
    }

    // Atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double valor;


    // Relações

    // Comanda 1 <-> 1 Cliente
    //@org.hibernate.annotations.ForeignKey(name = "cliente_id")
    @OneToOne(optional = true, mappedBy = "comanda")
    //@JsonIgnore
    private Cliente cliente;
    
    // 1 para N: Comanda -> Pedido
    //@org.hibernate.annotations.ForeignKey(name = "pedido_id")
    @OneToMany
    //@JsonIgnore
    private List<Pedido> pedidos;
    

    // Métodos

    public void atualizaValor () {}

    public void adicionaComanda () {}


    // Get / Set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
}
