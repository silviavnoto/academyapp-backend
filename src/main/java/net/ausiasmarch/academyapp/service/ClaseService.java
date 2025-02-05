package net.ausiasmarch.academyapp.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.academyapp.entity.AlumnoEntity;
import net.ausiasmarch.academyapp.entity.ClaseEntity;
import net.ausiasmarch.academyapp.entity.ProfesorEntity;
import net.ausiasmarch.academyapp.entity.Tipo;
import net.ausiasmarch.academyapp.exception.ResourceNotFoundException;
import net.ausiasmarch.academyapp.repository.ClaseRepository;

@Service
public class ClaseService implements ServiceInterface<ClaseEntity> {

    @Autowired
    private ClaseRepository oClaseRepository;

    @Autowired
    RandomService oRandomService;

    @Lazy
    @Autowired
    private AlumnoService oAlumnoService;

    @Lazy
    @Autowired
    private ProfesorService oProfesorService;

    private String[] arrAsignaturas = { "Literatura", "Tecnología", "Informática", "Francés", "Química", "Alemán",
            "Música", "Inglés", "Historia", "Ciencias Sociales" };

    private BigDecimal[] arrPrecios = { new BigDecimal("538.13"), new BigDecimal("326.28"), new BigDecimal("577.90"),
            new BigDecimal("744.87"), new BigDecimal("450.34"), new BigDecimal("45.45"), new BigDecimal("363.24"),
            new BigDecimal("668.22"), new BigDecimal("107.70"), new BigDecimal("368.00"), new BigDecimal("98.42"),
            new BigDecimal("802.07"), new BigDecimal("454.70"), new BigDecimal("601.82"), new BigDecimal("853.21"),
            new BigDecimal("436.71"), new BigDecimal("459.11"), new BigDecimal("579.15"), new BigDecimal("721.65"),
            new BigDecimal("857.91") };

    private String[] arrTipos = { "Individual", "Grupal" };

    private BigDecimal[] arrHoras = { new BigDecimal("0.5"), new BigDecimal("1.0"), new BigDecimal("2.0"),
            new BigDecimal("3.0"), new BigDecimal("4.0"), new BigDecimal("5.00"), new BigDecimal("6.00") };

    private Tipo getRandomTipo() {
        String tipoStr = arrTipos[oRandomService.getRandomInt(0, arrTipos.length - 1)].toUpperCase();
        try {
            return Tipo.valueOf(tipoStr);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("El valor '" + tipoStr + "' no es válido para Tipo");
        }
    }

    public Page<ClaseEntity> getPageXAlumno(Pageable oPageable, Optional<String> filter,
            Optional<Long> id_alumno) {
        if (filter.isPresent()) {
            if (id_alumno.isPresent()) {
                return oClaseRepository
                        .findByAlumnoIdAndAsignaturaContainingOrTipoContaining(
                                filter.get(), filter.get(), id_alumno.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Clase no encontrada");
            }
        } else {
            if (id_alumno.isPresent()) {
                return oClaseRepository.findByAlumnoId(id_alumno.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Clase no encontrada");
            }
        }
    }

    public Page<ClaseEntity> getPageXProfesor(Pageable oPageable, Optional<String> filter,
            Optional<Long> id_profesor) {
        if (filter.isPresent()) {
            if (id_profesor.isPresent()) {
                return oClaseRepository
                        .findByProfesorIdAndAsignaturaContainingOrTipoContaining(
                                filter.get(), filter.get(), id_profesor.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Clase no encontrada");
            }
        } else {
            if (id_profesor.isPresent()) {
                return oClaseRepository.findByProfesorId(id_profesor.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Clase no encontrada");
            }
        }
    }

    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            ClaseEntity oClaseEntity = new ClaseEntity();
            oClaseEntity.setAsignatura(arrAsignaturas[oRandomService.getRandomInt(0, arrAsignaturas.length - 1)]);
            oClaseEntity.setTipo(getRandomTipo());
            oClaseEntity.setPrecio(arrPrecios[oRandomService.getRandomInt(0, arrPrecios.length - 1)]);
            oClaseEntity.setHora(arrHoras[oRandomService.getRandomInt(0, arrHoras.length - 1)]);
            oClaseEntity.setAlumno(oAlumnoService.randomSelection());
            oClaseEntity.setProfesor(oProfesorService.randomSelection());
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


    public Optional<ClaseEntity> findById(Long id) {
        return oClaseRepository.findById(id);
    }

   /*  public ClaseEntity get(Long id) {
        return oClaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrado"));
        // return oClaseRepository.findById(id).get();
    }   */

    public ClaseEntity get(Long id) {
        ClaseEntity clase = oClaseRepository.findByIdWithAlumnoAndProfesor(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada"));
    
        // Verificamos si el alumno es null ANTES de devolver la clase
        if (clase.getAlumno() == null) {
            throw new ResourceNotFoundException("El alumno es nulo en esta clase");
        }
    
        return clase;
    }
    
    
    public Long count() {
        return oClaseRepository.count();
    }

    public Long delete(Long id) {
        oClaseRepository.deleteById(id);
        return 1L;
    }

   /* public ClaseEntity create(ClaseEntity oClaseEntity) {
        System.out.println("id_alumno: " + oClaseEntity.getAlumno().getId());
        if (oClaseEntity.getAlumno() == null) {
            throw new DataIntegrityViolationException("Alumno no puede ser nulo");
        }
        // oClaseEntity.setTipocuenta(oTipocuentaService.get(oCuentaEntity.getTipocuenta().getId()));
        return oClaseRepository.save(oClaseEntity);
    }   */ 

    public ClaseEntity create(ClaseEntity oClaseEntity) {
        if (oClaseEntity.getAlumno() == null || oClaseEntity.getAlumno().getId() == null) {
            throw new DataIntegrityViolationException("Alumno no puede ser nulo");
        }
        if (oClaseEntity.getProfesor() == null || oClaseEntity.getProfesor().getId() == null) {
            throw new DataIntegrityViolationException("Profesor no puede ser nulo");
        }
        return oClaseRepository.save(oClaseEntity);
    }
    

 /*    public ClaseEntity update(ClaseEntity oClaseEntity) {
        ClaseEntity oClaseEntityFromDatabase = oClaseRepository.findById(oClaseEntity.getId()).get();
        if (oClaseEntity.getAlumno() == null || oClaseEntity.getAlumno().getId() == null) {
            throw new DataIntegrityViolationException("id_alumno no puede ser nulo");
        }
        if (oClaseEntity.getAsignatura() != null) {
            oClaseEntityFromDatabase.setAsignatura(oClaseEntity.getAsignatura());
        }
        if (oClaseEntity.getPrecio() != null) {
            oClaseEntityFromDatabase.setPrecio(oClaseEntity.getPrecio());
        }
        if (oClaseEntity.getAlumno() != null) {
            oClaseEntityFromDatabase.setAlumno(oAlumnoService.get(oClaseEntity.getAlumno().getId()));
        }
        if (oClaseEntity.getProfesor() != null) {
            oClaseEntityFromDatabase.setProfesor(oProfesorService.get(oClaseEntity.getProfesor().getId()));
        }
        return oClaseRepository.save(oClaseEntityFromDatabase);
    }
*/

public ClaseEntity update(ClaseEntity oClaseEntity) {
    ClaseEntity oClaseEntityFromDatabase = oClaseRepository.findByIdWithAlumnoAndProfesor(oClaseEntity.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada"));

    if (oClaseEntity.getAlumno() == null || oClaseEntity.getAlumno().getId() == null) {
        throw new DataIntegrityViolationException("Alumno no puede ser nulo");
    }
    if (oClaseEntity.getProfesor() == null || oClaseEntity.getProfesor().getId() == null) {
        throw new DataIntegrityViolationException("Profesor no puede ser nulo");
    }

    if (oClaseEntity.getAsignatura() != null) {
        oClaseEntityFromDatabase.setAsignatura(oClaseEntity.getAsignatura());
    }
    if (oClaseEntity.getPrecio() != null) {
        oClaseEntityFromDatabase.setPrecio(oClaseEntity.getPrecio());
    }
    if (oClaseEntity.getHora() != null) {
        oClaseEntityFromDatabase.setHora(oClaseEntity.getHora());
    }
    oClaseEntityFromDatabase.setAlumno(oAlumnoService.get(oClaseEntity.getAlumno().getId()));
    oClaseEntityFromDatabase.setProfesor(oProfesorService.get(oClaseEntity.getProfesor().getId()));

    return oClaseRepository.save(oClaseEntityFromDatabase);
}

    public Long deleteAll() {
        oClaseRepository.deleteAll();
        return this.count();
    }

    public ClaseEntity randomSelection() {
        return oClaseRepository.findById((long) oRandomService.getRandomInt(1, (int) (long) this.count())).get();
    }

    public ClaseEntity setAlumno(Long id, Long idalumno) {
        ClaseEntity oClaseEntity = oClaseRepository.findById(id).get();
        AlumnoEntity oAlumnoEntity = oAlumnoService.get(idalumno);
        oClaseEntity.setAlumno(oAlumnoEntity);
        return oClaseRepository.save(oClaseEntity);
    }

    public ClaseEntity setProfesor(Long id, Long idprofesor) {
        ClaseEntity oClaseEntity = oClaseRepository.findById(id).get();
        ProfesorEntity oProfesorEntity = oProfesorService.get(idprofesor);
        oClaseEntity.setProfesor(oProfesorEntity);
        return oClaseRepository.save(oClaseEntity);
    }

}