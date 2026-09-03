package br.com.Igor.spring_boot_essentials.controller;


import br.com.Igor.spring_boot_essentials.dto.AvaliacaoFisicaDTO;
import br.com.Igor.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import br.com.Igor.spring_boot_essentials.exceptions.BadRequestException;
import br.com.Igor.spring_boot_essentials.exceptions.NotFoundException;
import br.com.Igor.spring_boot_essentials.service.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacoesFisicasController {

    private final AvaliacaoService avaliacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAvaliacao(@Valid @RequestBody AvaliacaoFisicaDTO avaliacaoFisicaDTO) throws NotFoundException, BadRequestException {
        avaliacaoService.criarAvaliacao(avaliacaoFisicaDTO);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvaliacoesFisicasProjection> getAllAvaliacoes() {
        return avaliacaoService.getAvaliacoes();
    }

}
