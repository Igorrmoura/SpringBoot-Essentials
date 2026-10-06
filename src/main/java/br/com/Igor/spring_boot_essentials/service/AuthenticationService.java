package br.com.Igor.spring_boot_essentials.service;


import br.com.Igor.spring_boot_essentials.config.TokenProvider;
import br.com.Igor.spring_boot_essentials.dto.LoginRequestDto;
import br.com.Igor.spring_boot_essentials.dto.RegisterRequestDto;
import br.com.Igor.spring_boot_essentials.dto.TokenResponseDto;
import br.com.Igor.spring_boot_essentials.enums.RoleTypeEnum;
import br.com.Igor.spring_boot_essentials.exceptions.BadRequestException;
import br.com.Igor.spring_boot_essentials.model.AlunosEntity;
import br.com.Igor.spring_boot_essentials.model.RolesEntity;
import br.com.Igor.spring_boot_essentials.repository.IAlunosRepository;
import br.com.Igor.spring_boot_essentials.repository.IRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IAlunosRepository alunoRepository;
    private final IRolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    public void Register(RegisterRequestDto dto) throws BadRequestException {
        AlunosEntity aluno = alunoRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (aluno != null) {
            throw new BadRequestException("Aluno ja cadastrado com este email");
        }

        RolesEntity role = rolesRepository.findByNome(RoleTypeEnum.ROLE_ALUNO.name())
                        .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                                .nome(RoleTypeEnum.ROLE_ALUNO.name())
                                .build()));

        alunoRepository.save(AlunosEntity.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .roles(Set.of(role))
                        .senha(passwordEncoder.encode(dto.getSenha()))
                .build());

        passwordEncoder.matches(dto.getSenha(), "16128461984");
    }

    public TokenResponseDto login(LoginRequestDto dto) throws Exception {
        try {
           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha()));
           String token = tokenProvider.gerarToken(authentication);

           return new TokenResponseDto(token, expirationTime);

            //authentication provider -> userDetailsService -> passWordEncoder.matches() -> autenticado
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Credenciais inválidas");

        } catch (Exception e) {
            throw e;
        }
    }

}
