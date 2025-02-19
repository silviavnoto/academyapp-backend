package net.ausiasmarch.academyapp.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import net.ausiasmarch.academyapp.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);

    Optional<UsuarioEntity> findByEmailAndPassword(String email, String password);

    Page<UsuarioEntity> findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
        String filter2, String filter3, String filter4, String filter5, Pageable oPageable);

    
    Page<UsuarioEntity> findByTipousuario_Id(Integer idTipousuario, Pageable pageable);

    

      
}
