package br.com.tabletap.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends Usuario {

    // Construtores
    public Admin () {}
    public Admin (String nome, String senha, String celular, UserRole role) {
        super.setNome(nome);
        super.setSenha(senha);
        super.setCelular(celular);
        super.setRole(role);
    }


    // Métodos

    private void editaItem () {}

    private void removeItem () {}

    private void adicionaItem () {}

    private void removeFuncionario () {}

    private void editaFuncionario () {}

}
