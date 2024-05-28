package br.com.tabletap.model;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("Funcionario")
public class Funcionario extends Usuario {

    // Construtores
    
    public Funcionario () {}
    public Funcionario (String nome, String senha, String celular, UserRole role) {
        super.setNome(nome);
        super.setSenha(senha);
        super.setCelular(celular);
        super.setRole(role);
    }


    // Relações

    // Funcionario 1 -> n Pedido
    @OneToMany private List<Pedido> pedidos;

    // Funcionario 1 -> n Notificacao
    @OneToMany private List<Notificacao> notificacoes;


    // Métodos

    private void criaNotificacao (String conteudo) {}

    private void comentaPedido () {}

    private void aceitaPedido () {}

    private void recusaPedido (String motivo) {}


    // Get / Set

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
