package br.com.tabletap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.access.event.PublicInvocationEvent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Carrinho implements Serializable {

    // Construtores

    public Carrinho () {}
    public Carrinho (Long id, BigDecimal valor) {
        this.id = id;
        this.valor = valor;
    }
    public Carrinho (Cliente cliente) {
        this.cliente = cliente;
    }

    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal valor;
    

    // Relações

    // 1 Carrinho <-> 1 Cliente
    //@org.hibernate.annotations.ForeignKey(name = "cliente_id")
    @OneToOne(optional = true, mappedBy = "carrinho")
    private Cliente cliente;

    // 1 Carrinho -> n ItemDados
    @OneToMany
    private List<ItemDados> itensCarrinho;
    
    /*
    // n Carrinho -> n Item
    // descrição e quantidade???
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "item_carrinho",
        uniqueConstraints = @UniqueConstraint (
            columnNames = {"item_id", "carrinho_id"},
            name = "unique_item_carrinho"
        ),
        joinColumns = @JoinColumn (
            name = "item_id",
            referencedColumnName = "id",
            table = "item",
            unique = false
        ),
        inverseJoinColumns = @JoinColumn (
            name = "carrinho_id",
            referencedColumnName = "id",
            table = "carrinho",
            unique = false
        )
    )
    private List<ItemInfo> itens;*/


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
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<ItemDados> getItens() {
        return itensCarrinho;
    }
    public void setItens(List<ItemDados> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }
    public List<ItemDados> getItensCarrinho() {
        return itensCarrinho;
    }
    public void setItensCarrinho(List<ItemDados> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }
}
