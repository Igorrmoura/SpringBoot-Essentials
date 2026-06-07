package br.com.Igor.spring_boot_essentials.repository;

import br.com.Igor.spring_boot_essentials.model.ExercicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExercicioRepository extends JpaRepository<ExercicioEntity, Integer> {

    List<ExercicioEntity> findAllByGrupoMuscular(String grupoMuscular);

    @Query(value = """
    SELECT e 
    FROM ExercicioEntity e 
    WHERE UPPER(e.grupoMuscular) = UPPER(:grupoMuscular)
""")
    List<ExercicioEntity> findAllByGrupoMuscularJpql(@Param("grupoMuscular") String grupoMuscular);

    @NativeQuery(value = """
    SELECT * 
    FROM exercicio 
    WHERE UPPER(grupo_muscular) = UPPER(:grupoMuscular)
""")
    List<ExercicioEntity> findAllByGrupoMuscularNative(@Param("grupoMuscular") String grupoMuscular);

}
