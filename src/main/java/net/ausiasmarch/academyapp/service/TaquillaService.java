package net.ausiasmarch.academyapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.academyapp.entity.TaquillaEntity;
import net.ausiasmarch.academyapp.exception.ResourceNotFoundException;
import net.ausiasmarch.academyapp.repository.TaquillaRepository;

@Service
public class TaquillaService implements ServiceInterface<TaquillaEntity> {

    @Autowired
    private TaquillaRepository oTaquillaRepository;

    @Lazy
    @Autowired
    private AlquilerService oAlquilerService;

    @Autowired
    RandomService oRandomService;

    private Integer[] arrNumeros = { 111, 112, 113, 114, 115, 116, 117, 118, 119, 120 };

    private String[] arrBloques = { "A", "B", "C", "AA", "BB", "CC" };

    private Boolean[] arrDisponible = { true, false };


    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            TaquillaEntity oTaquillaEntity = new TaquillaEntity();
            oTaquillaEntity.setNumero(arrNumeros[oRandomService.getRandomInt(0, arrNumeros.length - 1)]);
            oTaquillaEntity.setBloque(arrBloques[oRandomService.getRandomInt(0, arrBloques.length - 1)]);
            oTaquillaEntity.setDisponible(arrDisponible[oRandomService.getRandomInt(0, arrDisponible.length - 1)]);
            oTaquillaRepository.save(oTaquillaEntity);
        }
        return oTaquillaRepository.count();
    }

    public Page<TaquillaEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oTaquillaRepository
                    .findByNumeroContaining(
                            filter.get(), filter.get(),
                            oPageable);
        } else {
            return oTaquillaRepository.findAll(oPageable);
        }
    }

    public TaquillaEntity get(Long id) {
        return oTaquillaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taquilla no encontrada"));
        // return oTaquillaRepository.findById(id).get();
    }

    public Long count() {
        return oTaquillaRepository.count();
    }

    public Long delete(Long id) {
        oTaquillaRepository.deleteById(id);
        return 1L;
    }

    public TaquillaEntity create(TaquillaEntity oTaquillaEntity) {
        return oTaquillaRepository.save(oTaquillaEntity);
    }

    public TaquillaEntity update(TaquillaEntity oTaquillaEntity) {
        TaquillaEntity oTaquillaEntityFromDatabase = oTaquillaRepository.findById(oTaquillaEntity.getId()).get();
        if (oTaquillaEntity.getNumero() != null) {
            oTaquillaEntityFromDatabase.setNumero(oTaquillaEntity.getNumero());
        }
        if (oTaquillaEntity.getBloque() != null) {
            oTaquillaEntityFromDatabase.setBloque(oTaquillaEntity.getBloque());
        }
        oTaquillaEntityFromDatabase.setDisponible(oTaquillaEntity.isDisponible());

        return oTaquillaRepository.save(oTaquillaEntityFromDatabase);
    }

    public TaquillaEntity flip(Long id) {
        TaquillaEntity oTaquillaEntityFromDatabase = oTaquillaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taquilla no encontrada"));                
        oTaquillaEntityFromDatabase.setDisponible(!oTaquillaEntityFromDatabase.isDisponible());        
        return oTaquillaRepository.save(oTaquillaEntityFromDatabase);
    }

    public Long deleteAll() {
        oTaquillaRepository.deleteAll();
        return this.count();
    }

    public TaquillaEntity randomSelection() {
        return oTaquillaRepository.findById((long) oRandomService.getRandomInt(1, (int) (long) this.count())).get();
    }

}