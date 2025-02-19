package net.ausiasmarch.academyapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.academyapp.entity.ClaseEntity;
import net.ausiasmarch.academyapp.entity.ParticipaEntity;

public interface ClaseRepository extends JpaRepository<ClaseEntity, Long> {

    Page<ClaseEntity> findByAsignaturaContaining(
            String filter1, Pageable oPageable);

    Page<ClaseEntity> findAll(Pageable pageable);

    ClaseEntity save(ParticipaEntity oParticipaEntity);
       
}
