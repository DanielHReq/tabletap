package br.com.tabletap.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemDados implements Serializable {

    // Construtores

    public ItemDados () {
        this.quantidade = 1;
    }
    public ItemDados (String comentario, int quantidade) {
        this.comentario = comentario;
        this.quantidade = quantidade;
    }
    

    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String comentario;
    public int quantidade;
    

    // Relações

    // n ItemDados -> 1 Item
    @ManyToOne(optional = false)
    Item item;


    // Get / Set

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
}
