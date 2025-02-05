package net.ausiasmarch.academyapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.academyapp.entity.ProfesorEntity;
import net.ausiasmarch.academyapp.exception.ResourceNotFoundException;
import net.ausiasmarch.academyapp.repository.ProfesorRepository;

@Service
public class ProfesorService implements ServiceInterface<ProfesorEntity> {

    @Autowired
    private ProfesorRepository oProfesorRepository;

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
            ProfesorEntity oProfesorEntity = new ProfesorEntity();
            oProfesorEntity.setNombre(arrNombres[oRandomService.getRandomInt(0, arrNombres.length - 1)]);
            oProfesorEntity.setApellido1(arrApellidos[oRandomService.getRandomInt(0, arrApellidos.length - 1)]);
            oProfesorEntity.setApellido2(arrApellidos[oRandomService.getRandomInt(0, arrApellidos.length - 1)]);
            oProfesorEntity.setEmail(
                    "email" + oProfesorEntity.getNombre() + oRandomService.getRandomInt(999, 9999) + "@gmail.com");
            oProfesorEntity.setTelefono(arrTelefonos[oRandomService.getRandomInt(0, arrTelefonos.length - 1)]);
            oProfesorRepository.save(oProfesorEntity);
        }
        return oProfesorRepository.count();
    }

    public Page<ProfesorEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oProfesorRepository
                    .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContainingOrTelefonoContaining(
                            filter.get(), filter.get(), filter.get(), filter.get(), filter.get(), oPageable);
        } else {
            return oProfesorRepository.findAll(oPageable);
        }
    }

    public ProfesorEntity get(Long id) {
        return oProfesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));
        // return oProfesorRepository.findById(id).get();
    }

    public Long count() {
        return oProfesorRepository.count();
    }

    public Long delete(Long id) {
        oProfesorRepository.deleteById(id);
        return 1L;
    }

    public ProfesorEntity create(ProfesorEntity oProfesorEntity) {
        return oProfesorRepository.save(oProfesorEntity);
    }

    public ProfesorEntity update(ProfesorEntity oProfesorEntity) {
        ProfesorEntity oProfesorEntityFromDatabase = oProfesorRepository.findById(oProfesorEntity.getId()).get();
        if (oProfesorEntity.getNombre() != null) {
            oProfesorEntityFromDatabase.setNombre(oProfesorEntity.getNombre());
        }
        if (oProfesorEntity.getApellido1() != null) {
            oProfesorEntityFromDatabase.setApellido1(oProfesorEntity.getApellido1());
        }
        if (oProfesorEntity.getApellido2() != null) {
            oProfesorEntityFromDatabase.setApellido2(oProfesorEntity.getApellido2());
        }
        if (oProfesorEntity.getEmail() != null) {
            oProfesorEntityFromDatabase.setEmail(oProfesorEntity.getEmail());
        }
        return oProfesorRepository.save(oProfesorEntityFromDatabase);
    }

    public Long deleteAll() {
        oProfesorRepository.deleteAll();
        return this.count();
    }

    public ProfesorEntity randomSelection() {
        return oProfesorRepository.findById((long) oRandomService.getRandomInt(1, (int) (long) this.count())).get();
    }

}