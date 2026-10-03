package br.com.Igor.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequestDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
}
