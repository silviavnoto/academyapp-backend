package net.ausiasmarch.academyapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

import net.ausiasmarch.academyapp.entity.ClaseEntity;

public interface ClaseRepository extends JpaRepository<ClaseEntity, Long> {

    Page<ClaseEntity> findByAsignaturaContaining(
            String filter1, Pageable oPageable);

    Page<ClaseEntity> findByTipoContaining(
            String filter1, Pageable oPageable);

            

    Page<ClaseEntity> findByAlumnoId(
            Long id_alumno, Pageable oPageable);

    @Query(value = "SELECT * FROM clase WHERE asignatura LIKE %:strAsignatura% OR tipo LIKE %:strTipo% AND AND id_alumno=:id_alumno", nativeQuery = true)
    Page<ClaseEntity> findByAlumnoIdAndAsignaturaContainingOrTipoContaining(String strAsignatura,
            String strTipo, Long id_alumno, Pageable oPageable);

    Page<ClaseEntity> findByProfesorId(Long id_profesor, Pageable oPageable);

    @Query(value = "SELECT * FROM clase WHERE (asignatura LIKE %:strAsignatura% OR tipo LIKE %:strTipo%) AND id_profesor=:id_profesor", nativeQuery = true)
    Page<ClaseEntity> findByProfesorIdAndAsignaturaContainingOrTipoContaining(String strAsignatura,
            String strTipo, Long id_profesor, Pageable oPageable);


            @Query("SELECT c FROM ClaseEntity c LEFT JOIN FETCH c.alumno LEFT JOIN FETCH c.profesor WHERE c.id = :id")
            Optional<ClaseEntity> findByIdWithAlumnoAndProfesor(@org.springframework.data.repository.query.Param("id") Long id);
            


}
