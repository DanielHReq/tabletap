package br.com.tabletap.model;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("Cliente")
public class Cliente extends Usuario {

    // Construtores

    public Cliente() {
        //this.carrinho = new Carrinho(this);
        //this.comanda = new Comanda(this);
    }
    public Cliente(String celular, String senha, UserRole role) {
        this.celular = celular;
        this.senha = senha;
        this.role = role;
        //this.carrinho = new Carrinho(this);
        //this.comanda = new Comanda(this);
    }
    public Cliente(String celular, String senha, String nome, UserRole role) {
        this.celular = celular;
        this.senha = senha;
        this.nome = nome;
        this.role = role;
        //this.carrinho = new Carrinho(this);
        //this.comanda = new Comanda(this);
    }


    // Relações

    // Cliente n <-> 1 Mesa
    @ManyToOne private Mesa mesa;

    // Cliente 1 <-> 1 Comanda
    @OneToOne(optional = true)
    private Comanda comanda;

    // Cliente 1 <-> Carrinho
    @OneToOne(optional = true)
    private Carrinho carrinho;

    // Cliente 1 <-> n Notificacao
    @OneToMany(mappedBy = "cliente")
    private List<Notificacao> notificacoes;


    // Métodos

    public void adicionaItemCarrinho (Item item) {
        return;
    }

    public void removeItemCarrinho (/* referência ao item? */) {

    }

    public void esvaziaCarrinho () {}

    public void enviaPedido () {
        // rotina para enviar pedido
        this.esvaziaCarrinho();
    }

    public void fechaComanda () {}

    public void fechaMesa () {}

    public void recebeNotificação (Notificacao notificacao) {}

    public void comentaItem () {}

    public void editaItem () {}

}
