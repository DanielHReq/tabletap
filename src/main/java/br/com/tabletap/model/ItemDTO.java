package br.com.tabletap.model;

import java.math.BigDecimal;

public record ItemDTO(String nome, String descricao, BigDecimal valor, String tipo) {

}
