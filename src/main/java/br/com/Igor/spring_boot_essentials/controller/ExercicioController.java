package br.com.Igor.spring_boot_essentials.controller;

import br.com.Igor.spring_boot_essentials.dto.ExercicioDto;
import br.com.Igor.spring_boot_essentials.model.ExercicioEntity;
import br.com.Igor.spring_boot_essentials.service.ExercicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/exercicios")
@RequiredArgsConstructor
@Validated
public class ExercicioController {

    private final ExercicioService exercicioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExercicioEntity> findAll() {
        return exercicioService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExercicio(@Valid @RequestBody ExercicioDto exercicioDto) {
        exercicioService.save(exercicioDto);
    }

    @GetMapping("/grupos/{grupoMuscular}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExercicioEntity> getExerciciosByGrupoMuscular(@PathVariable String grupoMuscular) {
        return exercicioService.getByGrupoMuscular(grupoMuscular);
    }
}

