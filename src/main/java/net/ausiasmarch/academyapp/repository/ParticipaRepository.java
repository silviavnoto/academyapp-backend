package net.ausiasmarch.academyapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import net.ausiasmarch.academyapp.entity.ParticipaEntity;
import java.util.Optional;

public interface ParticipaRepository extends JpaRepository<ParticipaEntity, Long> {
    
    Page<ParticipaEntity> findByUsuarioId(Long usuarioId, Pageable pageable);
    
    Page<ParticipaEntity> findByClaseId(Long claseId, Pageable pageable);
    
    Optional<ParticipaEntity> findByUsuarioIdAndClaseId(Long usuarioId, Long claseId);
}
