package net.ausiasmarch.academyapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.academyapp.entity.AlumnoEntity;

public interface AlumnoRepository extends JpaRepository<AlumnoEntity, Long> {

    Page<AlumnoEntity> findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContainingOrTelefonoContaining(
            String filter2, String filter3, String filter4, String filter5, String filter6, Pageable oPageable);

}