package br.com.Igor.spring_boot_essentials.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "avaliacoes_fisicas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AvaliacoesFisicasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private BigDecimal peso;
    @Column(nullable = false)
    private BigDecimal altura;
    @Column(name = "porcentagem_Gordura_Corporal")
    private BigDecimal porcentagemDeGorduraCorporal;

}
