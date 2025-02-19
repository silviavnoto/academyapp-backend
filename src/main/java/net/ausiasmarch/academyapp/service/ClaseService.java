package net.ausiasmarch.academyapp.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.academyapp.entity.ClaseEntity;
import net.ausiasmarch.academyapp.entity.ParticipaEntity;
import net.ausiasmarch.academyapp.exception.ResourceNotFoundException;
import net.ausiasmarch.academyapp.exception.UnauthorizedAccessException;
import net.ausiasmarch.academyapp.repository.ClaseRepository;
import net.ausiasmarch.academyapp.repository.ParticipaRepository;

@Service
public class ClaseService implements ServiceInterface<ClaseEntity> {

    @Autowired
    private ClaseRepository oClaseRepository;

    @Autowired
    ParticipaRepository oParticipaRepository;

    @Autowired
    RandomService oRandomService;

    @Autowired
    ParticipaService oParticipaService;

    @Autowired
    AuthService oAuthService;
    


    private String[] arrAsignaturas = { "Literatura", "Tecnología", "Informática", "Francés", "Química", "Alemán",
            "Música", "Inglés", "Historia", "Ciencias Sociales" };

    private BigDecimal[] arrPrecios = { new BigDecimal("538.13"), new BigDecimal("326.28"), new BigDecimal("577.90"),
            new BigDecimal("744.87"), new BigDecimal("450.34"), new BigDecimal("45.45"), new BigDecimal("363.24"),
            new BigDecimal("668.22"), new BigDecimal("107.70"), new BigDecimal("368.00"), new BigDecimal("98.42"),
            new BigDecimal("802.07"), new BigDecimal("454.70"), new BigDecimal("601.82"), new BigDecimal("853.21"),
            new BigDecimal("436.71"), new BigDecimal("459.11"), new BigDecimal("579.15"), new BigDecimal("721.65"),
            new BigDecimal("857.91") };


    private BigDecimal[] arrHoras = { new BigDecimal("0.5"), new BigDecimal("1.0"), new BigDecimal("2.0"),
            new BigDecimal("3.0"), new BigDecimal("4.0"), new BigDecimal("5.00"), new BigDecimal("6.00") };



    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            ClaseEntity oClaseEntity = new ClaseEntity();
            oClaseEntity.setAsignatura(arrAsignaturas[oRandomService.getRandomInt(0, arrAsignaturas.length - 1)]);
            oClaseEntity.setPrecio(arrPrecios[oRandomService.getRandomInt(0, arrPrecios.length - 1)]);
            oClaseEntity.setHora(arrHoras[oRandomService.getRandomInt(0, arrHoras.length - 1)]);
   
            oClaseRepository.save(oClaseEntity);
        }
        return oClaseRepository.count();
    }

    public Page<ClaseEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oClaseRepository
                    .findByAsignaturaContaining(
                            filter.get(),
                            oPageable);
        } else {
            return oClaseRepository.findAll(oPageable);
        }
    }

   /*  public Page<ClaseEntity> getPageClases(Pageable oPageable) {
        if (oAuthService.isAdmin() || oAuthService.isContable()) {
            return oClaseRepository.findAll(oPageable);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para ver las clases");
        }
    } */

    public ClaseEntity get(Long id) {
        return oClaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrado"));
        // return oClaseRepository.findById(id).get();
    }  

    public Optional<ClaseEntity> findById(Long id) {
        return oClaseRepository.findById(id);
    }

 
 /*   public ClaseEntity get(Long id) {
        ClaseEntity clase = oClaseRepository.findByIdWithAlumnoAndProfesor(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada"));
    
        // Verificamos si el alumno es null ANTES de devolver la clase
        if (clase.getAlumno() == null) {
            throw new ResourceNotFoundException("El alumno es nulo en esta clase");
        }
    
        return clase;
    }  */
    
    
    public Long count() {
        return oClaseRepository.count();
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin() || oAuthService.isContable()) {
            oClaseRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar la clase");
        }
    }
    


    public ClaseEntity create(ClaseEntity oClaseEntity) {

        if (oAuthService.isAdmin() || oAuthService.isContable()) {
            if (oClaseEntity.getParticipas() == null) {
                throw new DataIntegrityViolationException("El usuario no puede ser nulo");
            }
            return oClaseRepository.save(oClaseEntity);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para crear una clase");
        }
    }
    

    public ClaseEntity update(ClaseEntity oClaseEntity) {
        if (oClaseEntity.getId() == null) {
            throw new DataIntegrityViolationException("El ID de la clase no puede ser null");
        }
        if (oAuthService.isAdmin() || oAuthService.isContable()) {
            ClaseEntity oClaseEntityFromDatabase = oClaseRepository.findById(oClaseEntity.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada"));
    
            if (oClaseEntity.getParticipas() == null) {
                throw new DataIntegrityViolationException("El id del usuario no puede ser nulo");
            }
            if (oClaseEntity.getAsignatura() != null) {
                oClaseEntityFromDatabase.setAsignatura(oClaseEntity.getAsignatura());
            }
            if (oClaseEntity.getPrecio() != null) {
                oClaseEntityFromDatabase.setPrecio(oClaseEntity.getPrecio());
            }
    
            return oClaseRepository.save(oClaseEntityFromDatabase);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para actualizar una clase");
        }
    }
    
    



    public Long deleteAll() {
        oClaseRepository.deleteAll();
        return this.count();
    }

    public ClaseEntity randomSelection() {
        return oClaseRepository.findById((long) oRandomService.getRandomInt(1, (int) (long) this.count())).get();
    }

    public ClaseEntity setParticipa(Long id, Long id_participa) {
        ClaseEntity oClaseEntity = oClaseRepository.findById(id).get();
        ParticipaEntity oParticipaEntity = oParticipaService.get(id_participa);
       // oParticipaEntity.setParticipa(oParticipaEntity);
       
       return oClaseRepository.save(oParticipaEntity);
    }

    public Page<ParticipaEntity> getUsuariosEnClase(Long claseId, Pageable pageable) {
        return oParticipaRepository.findByClaseId(claseId, pageable);
    }
    
}


