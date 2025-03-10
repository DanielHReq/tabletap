package br.com.tabletap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item implements Serializable {

    // Construtores

    public Item () {}
    public Item (String nome, String descricao, BigDecimal valor, String tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }


    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String nome;
    public String descricao;
    public BigDecimal valor;
    public String tipo;
    public Blob imagem;


    // Get / Set

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Blob getImagem() {
        return imagem;
    }
    public void setImagem(Blob imagem) {
        this.imagem = imagem;
    }
}
