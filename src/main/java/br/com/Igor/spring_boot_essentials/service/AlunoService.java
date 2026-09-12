package br.com.Igor.spring_boot_essentials.service;

import br.com.Igor.spring_boot_essentials.dto.AlunoDto;
import br.com.Igor.spring_boot_essentials.exceptions.BadRequestException;
import br.com.Igor.spring_boot_essentials.exceptions.NotFoundException;
import br.com.Igor.spring_boot_essentials.model.AlunosEntity;
import br.com.Igor.spring_boot_essentials.model.AvaliacoesFisicasEntity;
import br.com.Igor.spring_boot_essentials.model.TreinosEntity;
import br.com.Igor.spring_boot_essentials.repository.IAlunosRepository;
import br.com.Igor.spring_boot_essentials.repository.IAvaliacoesFisicasRepository;
import br.com.Igor.spring_boot_essentials.repository.ITreinosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final IAlunosRepository alunoRepository;
    private final ITreinosRepository treinosRepository;
    private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;

    public void criarAluno(AlunoDto alunoDto) throws BadRequestException {
        AlunosEntity aluno = alunoRepository.findByEmail(alunoDto.getEmail())
                .orElse(null);

        if (aluno != null) {
            throw new BadRequestException("Aluno ja cadastrado com este email");
        }

        alunoRepository.save(AlunosEntity.builder()
                .nome(alunoDto.getNome())
                .email(alunoDto.getEmail())
                .build());

    }

    public AvaliacoesFisicasEntity getAlunoAvaliacao(Integer alunoId) throws NotFoundException {
        AlunosEntity aluno = alunoRepository.findByIdFetch(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        AvaliacoesFisicasEntity avaliacao = aluno.getAvaliacoesFisicas();
        if (avaliacao == null) {
            throw new NotFoundException("Avaliação física não encontrada para o aluno");
        }
        return avaliacao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletarAluno(Integer alunoId) throws NotFoundException {
        AlunosEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        //1. deletar treinos do aluno
        List<Integer> treinosAlunoIds = aluno.getTreinos().stream()
                .map(TreinosEntity::getId)
                .toList();
        treinosRepository.deleteAllById(treinosAlunoIds);

        //2. deletar o aluno
        alunoRepository.deleteById(alunoId);

        if (1 == 1) {
            throw new RuntimeException("Erro simulado para teste de rollback");
        }
        //3. deletar a avaliação física do aluno
        avaliacoesFisicasRepository.deleteById(aluno.getAvaliacoesFisicas().getId());

    }
}
