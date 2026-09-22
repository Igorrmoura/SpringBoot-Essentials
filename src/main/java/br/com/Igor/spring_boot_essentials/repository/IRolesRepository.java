package br.com.Igor.spring_boot_essentials.repository;

import br.com.Igor.spring_boot_essentials.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolesRepository extends JpaRepository<RolesEntity, Integer> {
}
