package br.com.Igor.spring_boot_essentials.repository;

import br.com.Igor.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import br.com.Igor.spring_boot_essentials.model.AvaliacoesFisicasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAvaliacoesFisicasRepository extends JpaRepository<AvaliacoesFisicasEntity, Integer> {

    @NativeQuery(value = """ 
        SELECT 
            a.id AS id,
            a.nome AS nomeAluno,
            af.id AS idAvaliacao,
            af.peso AS peso,
            af.altura AS altura,
            af.porcentagem_Gordura_Corporal AS porcentagemGorduraCorporal
        FROM avaliacoes_fisicas af
        INNER JOIN alunos a
            ON a.avaliacao_fisica_id = af.id
        """)
    List<AvaliacoesFisicasProjection> getAllAvaliacoes();
}
