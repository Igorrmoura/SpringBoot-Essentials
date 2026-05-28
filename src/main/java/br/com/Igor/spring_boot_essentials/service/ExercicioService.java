package br.com.Igor.spring_boot_essentials.service;

import br.com.Igor.spring_boot_essentials.dto.ExercicioDto;
import br.com.Igor.spring_boot_essentials.model.ExercicioEntity;
import br.com.Igor.spring_boot_essentials.repository.IExercicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final IExercicioRepository exercicioRepository;

    public List<ExercicioEntity> findAll() {
        return exercicioRepository.findAll();
    }

    public void save(ExercicioDto exercicioDto) {
        ExercicioEntity exercicio = ExercicioEntity.builder()
                .nome(exercicioDto.getNome())
                .grupoMuscular(exercicioDto.getGrupoMuscular())
                        .build();

        exercicioRepository.save(exercicio);
    }
}
