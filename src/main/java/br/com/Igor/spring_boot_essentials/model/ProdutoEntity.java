package br.com.Igor.spring_boot_essentials.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProdutoEntity {

    private Integer id;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;

}
