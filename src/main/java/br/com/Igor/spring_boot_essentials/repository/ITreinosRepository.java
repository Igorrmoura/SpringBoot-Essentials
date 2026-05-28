package br.com.Igor.spring_boot_essentials.repository;

import br.com.Igor.spring_boot_essentials.model.TreinosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITreinosRepository extends JpaRepository<TreinosEntity, Integer> {
}
