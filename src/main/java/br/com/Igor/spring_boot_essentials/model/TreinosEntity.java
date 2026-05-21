package br.com.Igor.spring_boot_essentials.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "treino")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TreinosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunosEntity aluno;

    @ManyToMany
    @JoinTable(
            name = "Treinos_exercicios",
            joinColumns = @JoinColumn(name = "treino_id"),
            inverseJoinColumns = @JoinColumn(name = "exercicios_id")
    )
    private Set<ExercicioEntity> exercicios = new HashSet<>();
}
