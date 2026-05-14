package br.com.Igor.spring_boot_essentials.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProdutoDto {

    private String name;
    private BigDecimal price;
    private Integer amount;
}
