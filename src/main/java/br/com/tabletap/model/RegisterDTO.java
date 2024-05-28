package br.com.tabletap.model;

// login é o celular
public record RegisterDTO(String login, String senha, UserRole role, String nome) {

}
