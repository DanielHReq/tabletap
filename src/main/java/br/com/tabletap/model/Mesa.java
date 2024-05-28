package br.com.tabletap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Mesa implements Serializable {

    // Construtores

    public Mesa () {}
    public Mesa (Long id, BigDecimal valor) {
        this.id = id;
        this.valor = valor;
    }

    // Atributos
    
    @Id
    Long id;

    private BigDecimal valor;


    // Relações

    // Mesa 1 -> n Cliente
    @OneToMany(mappedBy = "mesa")
    private List<Cliente> clientes;


    // Get / Set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
