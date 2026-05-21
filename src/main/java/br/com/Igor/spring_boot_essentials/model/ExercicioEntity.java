package br.com.Igor.spring_boot_essentials.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "exercicio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExercicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "grupo muscular", nullable = false)
    private String grupo;

}


