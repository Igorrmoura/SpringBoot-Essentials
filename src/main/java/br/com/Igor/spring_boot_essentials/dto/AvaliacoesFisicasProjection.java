package br.com.Igor.spring_boot_essentials.dto;

import java.math.BigDecimal;

public interface AvaliacoesFisicasProjection {
    Integer getId();
    String getNomeAluno();
    Integer getIdAvaliacao();
    BigDecimal getPeso();
    BigDecimal getAltura();
    BigDecimal getPorcentagemGorduraCorporal();
}
