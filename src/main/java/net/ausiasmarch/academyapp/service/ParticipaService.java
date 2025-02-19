package net.ausiasmarch.academyapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.ausiasmarch.academyapp.entity.ParticipaEntity;
import net.ausiasmarch.academyapp.repository.ParticipaRepository;
import java.util.Optional;
import java.util.Random;

@Service
public class ParticipaService {

    @Autowired
    private ParticipaRepository participaRepository;

    public Page<ParticipaEntity> getPage(Pageable pageable, Optional<String> filter) {
        return participaRepository.findAll(pageable);
    }

    public Page<ParticipaEntity> getByUsuario(Pageable pageable, Long usuarioId) {
        return participaRepository.findByUsuarioId(usuarioId, pageable);
    }

    public Page<ParticipaEntity> getByClase(Pageable pageable, Long claseId) {
        return participaRepository.findByClaseId(claseId, pageable);
    }

    public ParticipaEntity get(Long id) {
        return participaRepository.findById(id).orElseThrow();
    }

    public Long count() {
        return participaRepository.count();
    }

    public ParticipaEntity create(ParticipaEntity participaEntity) {
        return participaRepository.save(participaEntity);
    }

    public ParticipaEntity update(ParticipaEntity participaEntity) {
        if (participaRepository.existsById(participaEntity.getId())) {
            return participaRepository.save(participaEntity);
        } else {
            throw new RuntimeException("Participación no encontrada.");
        }
    }

    public Long delete(Long id) {
        if (participaRepository.existsById(id)) {
            participaRepository.deleteById(id);
            return id;
        } else {
            throw new RuntimeException("Participación no encontrada.");
        }
    }

    public Long deleteAll() {
        Long count = participaRepository.count();
        participaRepository.deleteAll();
        return count;
    }

        public ParticipaEntity randomSelection() {
        // Implement the logic to return a random ParticipaEntity
        // For example:
        List<ParticipaEntity> allParticipas = participaRepository.findAll();
        return allParticipas.get(new Random().nextInt(allParticipas.size()));
    }
}
