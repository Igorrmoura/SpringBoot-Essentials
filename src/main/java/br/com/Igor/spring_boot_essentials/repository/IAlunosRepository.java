package br.com.Igor.spring_boot_essentials.repository;

import br.com.Igor.spring_boot_essentials.model.AlunosEntity;
import br.com.Igor.spring_boot_essentials.model.ExercicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAlunosRepository extends JpaRepository<AlunosEntity, Integer> {
    Optional<AlunosEntity> findByEmail(String email);

    @Query("""
       SELECT a
       FROM AlunosEntity a
       LEFT JOIN FETCH a.avaliacoesFisicas
       WHERE a.id = :alunoId
       """)
    Optional<AlunosEntity> findByIdFetch(
            @Param("alunoId") Integer id
    );
}
