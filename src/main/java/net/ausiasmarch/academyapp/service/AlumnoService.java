package net.ausiasmarch.academyapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.academyapp.entity.AlumnoEntity;
import net.ausiasmarch.academyapp.exception.ResourceNotFoundException;
import net.ausiasmarch.academyapp.repository.AlumnoRepository;

@Service
public class AlumnoService implements ServiceInterface<AlumnoEntity> {

    @Autowired
    private AlumnoRepository oAlumnoRepository;

    @Lazy
    @Autowired
    private ClaseService oClaseService;

    @Autowired
    RandomService oRandomService;

    private String[] arrNombres = { "Pepe", "Laura", "Ignacio", "Maria", "Lorenzo", "Carmen", "Rosa", "Paco", "Luis",
            "Ana", "Rafa", "Manolo", "Lucia", "Marta", "Sara", "Rocio" };

    private String[] arrApellidos = { "Sancho", "Gomez", "Pérez", "Rodriguez", "Garcia", "Fernandez", "Lopez",
            "Martinez", "Sanchez", "Gonzalez", "Gimenez", "Feliu", "Gonzalez", "Hermoso", "Vidal", "Escriche",
            "Moreno" };

    private String[] arrTelefonos = { "679478081", "997485140", "634619054", "776967688", "659941577", "785864212", "956574481", "779427797", "942599011", "957242447" };


    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            AlumnoEntity oAlumnoEntity = new AlumnoEntity();
            oAlumnoEntity.setNombre(arrNombres[oRandomService.getRandomInt(0, arrNombres.length - 1)]);
            oAlumnoEntity.setApellido1(arrApellidos[oRandomService.getRandomInt(0, arrApellidos.length - 1)]);
            oAlumnoEntity.setApellido2(arrApellidos[oRandomService.getRandomInt(0, arrApellidos.length - 1)]);
            oAlumnoEntity.setEmail(
                    "email" + oAlumnoEntity.getNombre() + oRandomService.getRandomInt(999, 9999) + "@gmail.com");
            oAlumnoEntity.setTelefono(arrTelefonos[oRandomService.getRandomInt(0, arrTelefonos.length - 1)]);
            oAlumnoRepository.save(oAlumnoEntity);
        }
        return oAlumnoRepository.count();
    }

    public Page<AlumnoEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oAlumnoRepository
                    .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContainingOrTelefonoContaining(
                            filter.get(), filter.get(), filter.get(), filter.get(), filter.get(),
                            oPageable);
        } else {
            return oAlumnoRepository.findAll(oPageable);
        }
    }

    public AlumnoEntity get(Long id) {
        return oAlumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado"));
        // return oAlumnoRepository.findById(id).get();
    }

    public Long count() {
        return oAlumnoRepository.count();
    }

    public Long delete(Long id) {
        oAlumnoRepository.deleteById(id);
        return 1L;
    }

    public AlumnoEntity create(AlumnoEntity oAlumnoEntity) {
        return oAlumnoRepository.save(oAlumnoEntity);
    }

    public AlumnoEntity update(AlumnoEntity oAlumnoEntity) {
        AlumnoEntity oAlumnoEntityFromDatabase = oAlumnoRepository.findById(oAlumnoEntity.getId()).get();
        if (oAlumnoEntity.getNombre() != null) {
            oAlumnoEntityFromDatabase.setNombre(oAlumnoEntity.getNombre());
        }
        if (oAlumnoEntity.getApellido1() != null) {
            oAlumnoEntityFromDatabase.setApellido1(oAlumnoEntity.getApellido1());
        }
        if (oAlumnoEntity.getApellido2() != null) {
            oAlumnoEntityFromDatabase.setApellido2(oAlumnoEntity.getApellido2());
        }
        if (oAlumnoEntity.getEmail() != null) {
            oAlumnoEntityFromDatabase.setEmail(oAlumnoEntity.getEmail());
        }
        return oAlumnoRepository.save(oAlumnoEntityFromDatabase);
    }

    public Long deleteAll() {
        oAlumnoRepository.deleteAll();
        return this.count();
    }

    public AlumnoEntity randomSelection() {
        return oAlumnoRepository.findById((long) oRandomService.getRandomInt(1, (int) (long) this.count())).get();
    }

}