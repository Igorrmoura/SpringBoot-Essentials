package br.com.Igor.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlunoDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
}
