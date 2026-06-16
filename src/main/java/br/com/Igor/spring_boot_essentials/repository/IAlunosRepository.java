package br.com.Igor.spring_boot_essentials.repository;

import br.com.Igor.spring_boot_essentials.model.AlunosEntity;
import br.com.Igor.spring_boot_essentials.model.ExercicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlunosRepository extends JpaRepository<AlunosEntity, Integer> {
        List<AlunosEntity> findAllByNome(String nome);
}
