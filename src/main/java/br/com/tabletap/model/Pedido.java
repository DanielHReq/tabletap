package br.com.tabletap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido implements Serializable {

    // Construtores

    public Pedido () {}
    public Pedido (Carrinho carrinho) {
        this.itensPedido = carrinho.getItensCarrinho();
        this.valor = carrinho.getValor();
    }


    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal valor;
    private String status;
    private String comentario;
    

    // Relações

    // Pedido n <-> 1 Comanda
    @ManyToOne(optional = false)
    private Comanda comanda;
    
    // 1 Pedido -> n ItemDados
    @OneToMany
    private List<ItemDados> itensPedido;
    
    /*
    // n Pedido -> n Item
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "item_pedido",
        uniqueConstraints = @UniqueConstraint (
            columnNames = {"item_id", "pedido_id"},
            name = "unique_item_pedido"
        ),
        joinColumns = @JoinColumn (
            name = "item_id",
            referencedColumnName = "id",
            table = "item",
            unique = false
        ),
        inverseJoinColumns = @JoinColumn (
            name = "pedido_id",
            referencedColumnName = "id",
            table = "pedido",
            unique = false
        )
    )
    private List<Item> itens; */
    

    // Métodos

    void atualizaValor () {}

    void atualizaStatus () {}

    void adicionaComentario (String comentario) {}


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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Comanda getComanda() {
        return comanda;
    }
    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }
    public List<ItemDados> getItensPedido() {
        return itensPedido;
    }
    public void setItensPedido(List<ItemDados> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
