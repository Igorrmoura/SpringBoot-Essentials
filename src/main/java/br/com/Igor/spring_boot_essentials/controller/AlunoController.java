package br.com.Igor.spring_boot_essentials.controller;

import br.com.Igor.spring_boot_essentials.dto.AlunoDto;
import br.com.Igor.spring_boot_essentials.exceptions.BadRequestException;
import br.com.Igor.spring_boot_essentials.exceptions.NotFoundException;
import br.com.Igor.spring_boot_essentials.model.AvaliacoesFisicasEntity;
import br.com.Igor.spring_boot_essentials.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
@Validated
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@Valid @RequestBody AlunoDto alunoDto) throws BadRequestException {
        alunoService.criarAluno(alunoDto);
    }

    @PreAuthorize("#alunoId == authentications.principal.id or hasRole('ADMIN')")
    @GetMapping("/{alunoId}/avaliacao")
    public AvaliacoesFisicasEntity getAvaliacaoFisica(@PathVariable Integer alunoId) throws NotFoundException {
        return alunoService.getAlunoAvaliacao(alunoId);
    }
    @DeleteMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAluno(@PathVariable Integer alunoId) throws NotFoundException {
        alunoService.deletarAluno(alunoId);
    }
}
