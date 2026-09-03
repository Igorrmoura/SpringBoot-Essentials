package br.com.Igor.spring_boot_essentials.service;


import br.com.Igor.spring_boot_essentials.dto.AvaliacaoFisicaDTO;
import br.com.Igor.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import br.com.Igor.spring_boot_essentials.exceptions.BadRequestException;
import br.com.Igor.spring_boot_essentials.exceptions.NotFoundException;
import br.com.Igor.spring_boot_essentials.model.AlunosEntity;
import br.com.Igor.spring_boot_essentials.model.AvaliacoesFisicasEntity;
import br.com.Igor.spring_boot_essentials.repository.IAlunosRepository;
import br.com.Igor.spring_boot_essentials.repository.IAvaliacoesFisicasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final IAlunosRepository alunosRepository;
    private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;

    public void criarAvaliacao(AvaliacaoFisicaDTO avaliacaoFisicaDTO) throws NotFoundException {
        AlunosEntity aluno = alunosRepository.findById(avaliacaoFisicaDTO.getAlunoId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        AvaliacoesFisicasEntity avaliacaoFisica = aluno.getAvaliacoesFisicas();
        if (avaliacaoFisica  != null) {
            throw new BadRequestException("Avaliação física já cadastrada para o aluno");
        }
        avaliacaoFisica = AvaliacoesFisicasEntity.builder()
                .peso(avaliacaoFisicaDTO.getPeso())
                .altura(avaliacaoFisicaDTO.getAltura())
                .porcentagemDeGorduraCorporal(avaliacaoFisicaDTO.getPorcentagemGorduraCorporal())
                .build();


        aluno.setAvaliacoesFisicas(avaliacaoFisica);
        alunosRepository.save(aluno);
    }

    public List<AvaliacoesFisicasProjection> getAvaliacoes() {
        return avaliacoesFisicasRepository.getAllAvaliacoes();
    }
}
