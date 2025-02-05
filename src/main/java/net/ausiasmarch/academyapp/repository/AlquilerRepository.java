package net.ausiasmarch.academyapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.academyapp.entity.AlquilerEntity;

public interface AlquilerRepository extends JpaRepository<AlquilerEntity, Long>{
    Page<AlquilerEntity> findByInicioContainingOrFinContaining(
        String filter2, String filter3, Pageable oPageable);

}
