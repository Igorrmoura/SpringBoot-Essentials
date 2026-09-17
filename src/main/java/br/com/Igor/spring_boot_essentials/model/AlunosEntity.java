package br.com.Igor.spring_boot_essentials.model;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "alunos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AlunosEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;
    private String senha;

    // LAZY: Carrega a avaliação física apenas quando acessada.
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "avaliacao_fisica_id")
    private AvaliacoesFisicasEntity avaliacoesFisicas;

    // LAZY: Carrega os treinos apenas quando necessário, evitando sobrecarga de dados.
    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
    private Set<TreinosEntity> treinos = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "alunos_roles",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RolesEntity> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  roles;
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
