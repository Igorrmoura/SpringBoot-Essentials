package br.com.Igor.spring_boot_essentials.service;

import br.com.Igor.spring_boot_essentials.dto.TreinoDto;
import br.com.Igor.spring_boot_essentials.exceptions.BadRequestException;
import br.com.Igor.spring_boot_essentials.exceptions.NotFoundException;
import br.com.Igor.spring_boot_essentials.model.AlunosEntity;
import br.com.Igor.spring_boot_essentials.model.ExercicioEntity;
import br.com.Igor.spring_boot_essentials.model.TreinosEntity;
import br.com.Igor.spring_boot_essentials.repository.IAlunosRepository;
import br.com.Igor.spring_boot_essentials.repository.IExercicioRepository;
import br.com.Igor.spring_boot_essentials.repository.ITreinosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final IAlunosRepository alunosRepository;
    private final IExercicioRepository exercicioRepository;
    private final ITreinosRepository treinosRepository;

    public void criarTreino(TreinoDto treinoDto) throws NotFoundException {
        Set <ExercicioEntity> exercicios = new HashSet<>();
        AlunosEntity alunos = alunosRepository.findById(treinoDto.getAlunoId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        TreinosEntity treino = treinosRepository.findByNomeAndId(treinoDto.getNome(), treinoDto.getAlunoId())
                .orElse(null);

        if (treino != null) {
            throw new BadRequestException("já existe um treino com esse nome para este aluno");
        }

        for (Integer exercicioId : treinoDto.getExerciciosId()) {
            ExercicioEntity exercicio = exercicioRepository.findById(exercicioId)
                    .orElseThrow(() -> new NotFoundException(String.format("Exercício com ID %d não encontrado", exercicioId)));

            exercicios.add(exercicio);
        }
        treino = TreinosEntity.builder()
                .nome(treinoDto.getNome())
                .aluno(alunos)
                .exercicios(exercicios)
                .build();

        treinosRepository.save(treino);
    }
}
