package net.ausiasmarch.academyapp.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import net.ausiasmarch.academyapp.entity.UsuarioEntity;
import net.ausiasmarch.academyapp.exception.ResourceNotFoundException;
import net.ausiasmarch.academyapp.exception.UnauthorizedAccessException;
import net.ausiasmarch.academyapp.repository.UsuarioRepository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@AllArgsConstructor
public class UsuarioService {

    HttpServletRequest oHttpServletRequest;

    UsuarioRepository oUsuarioRepository;

    AuthService oAuthService;

    public UsuarioEntity getByEmail(String email) {
        UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con email " + email + " no existe"));
        if (oAuthService.isContableWithItsOwnData(oUsuarioEntity.getId()) || oAuthService.isAdmin()
                || oAuthService.isAuditorWithItsOwnData(oUsuarioEntity.getId())) {
            return oUsuarioEntity;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para ver el usuario");
        }
    }

    public UsuarioEntity get(Long id) {
        Optional<UsuarioEntity> usuario = oUsuarioRepository.findById(id);
    
        if (usuario.isPresent()) {
            if (oAuthService.isAdmin() || oAuthService.isContable()) {  
                // Ahora permite acceso si el usuario es ADMIN o CONTABLE
                return usuario.get();
            } else {
                throw new UnauthorizedAccessException("No tienes permisos para ver el usuario");
            }
        } else {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
    }
    
    
    
    

    public Page<UsuarioEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (oAuthService.isAdmin()) {
            if (filter.isPresent()) {
                return oUsuarioRepository
                        .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
                                filter.get(), filter.get(), filter.get(), filter.get(),
                                oPageable);
            } else {
                return oUsuarioRepository.findAll(oPageable);
            }

        } else if (oAuthService.isContable()) {
            return oUsuarioRepository.findByTipousuario_Id(3, oPageable);
        } else if (oAuthService.isAuditor()) {
            throw new UnauthorizedAccessException("Los Alumnos no tienen permisos para listar usuarios.");
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para ver los usuarios");
        }

    }


    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los usuarios");
        } else {
            return oUsuarioRepository.count();
        }
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oUsuarioRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el usuario");
        }
    }

    public UsuarioEntity create(UsuarioEntity oUsuarioEntity) {

        if (oAuthService.isAdmin()) {
            return oUsuarioRepository.save(oUsuarioEntity);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para crear el usuario");
        }
    }

    public UsuarioEntity update(UsuarioEntity oUsuarioEntity) {
        if (oAuthService.isContableWithItsOwnData(oUsuarioEntity.getId()) || oAuthService.isAdmin()
                || oAuthService.isAuditorWithItsOwnData(oUsuarioEntity.getId())) {
            UsuarioEntity oUsuarioEntityFromDatabase = oUsuarioRepository.findById(oUsuarioEntity.getId()).get();
            if (oUsuarioEntity.getNombre() != null) {
                oUsuarioEntityFromDatabase.setNombre(oUsuarioEntity.getNombre());
            }
            if (oUsuarioEntity.getApellido1() != null) {
                oUsuarioEntityFromDatabase.setApellido1(oUsuarioEntity.getApellido1());
            }
            if (oUsuarioEntity.getApellido2() != null) {
                oUsuarioEntityFromDatabase.setApellido2(oUsuarioEntity.getApellido2());
            }
            if (oUsuarioEntity.getEmail() != null) {
                oUsuarioEntityFromDatabase.setEmail(oUsuarioEntity.getEmail());
            }
            return oUsuarioRepository.save(oUsuarioEntityFromDatabase);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el usuario");
        }
    }

    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los usuarios");
        } else {
            oUsuarioRepository.deleteAll();
            return this.count();
        }
    }
}
