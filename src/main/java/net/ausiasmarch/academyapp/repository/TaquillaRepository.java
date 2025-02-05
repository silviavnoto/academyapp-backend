package net.ausiasmarch.academyapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ausiasmarch.academyapp.entity.TaquillaEntity;

public interface TaquillaRepository extends JpaRepository<TaquillaEntity, Long> {

    Page<TaquillaEntity> findByNumeroContaining(
            String filter2, String filter3, Pageable oPageable);

 @Modifying
 @Query(value = "UPDATE taquilla SET disponible = :disponible WHERE id = :id", nativeQuery = true)
 void actualizarDisponibilidad(@Param("id") Long id, @Param("disponible") Boolean disponible);
 
}