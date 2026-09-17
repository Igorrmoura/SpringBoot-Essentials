package br.com.Igor.spring_boot_essentials.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RolesEntity implements GrantedAuthority {

    @Id
    private Integer id;
    private String nome;

    @Override
    public @Nullable String getAuthority() {
        return "";
    }
}
