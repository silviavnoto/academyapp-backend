package net.ausiasmarch.academyapp.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ausiasmarch.academyapp.entity.AlquilerEntity;
import net.ausiasmarch.academyapp.exception.ResourceNotFoundException;
import net.ausiasmarch.academyapp.repository.AlquilerRepository;
import net.ausiasmarch.academyapp.repository.TaquillaRepository;

@Service
public class AlquilerService implements ServiceInterface<AlquilerEntity> {

    @Autowired
    private AlquilerRepository oAlquilerRepository;

    @Autowired
    private TaquillaRepository oTaquillaRepository;

    @Autowired
    RandomService oRandomService;

    @Lazy
    @Autowired
    private AlumnoService oAlumnoService;
    
    @Lazy
    @Autowired
    private TaquillaService oTaquillaService;


    private LocalDateTime[] arrFechas = {
        LocalDateTime.of(2023, 1, 1, 10, 0),
            LocalDateTime.of(2023, 2, 2, 12, 30),
            LocalDateTime.of(2023, 3, 3, 14, 15),
            LocalDateTime.of(2023, 4, 4, 16, 45),
            LocalDateTime.of(2023, 5, 5, 18, 0),
            LocalDateTime.of(2023, 6, 6, 20, 30),
            LocalDateTime.of(2023, 7, 7, 22, 15),
            LocalDateTime.of(2023, 8, 8, 9, 45),
            LocalDateTime.of(2023, 9, 9, 11, 0),
            LocalDateTime.of(2023, 10, 10, 13, 30),
            LocalDateTime.of(2023, 11, 11, 15, 15),
            LocalDateTime.of(2023, 12, 12, 17, 45),
            LocalDateTime.of(2024, 1, 13, 19, 0),
            LocalDateTime.of(2024, 2, 14, 21, 30),
            LocalDateTime.of(2024, 3, 15, 23, 15),
            LocalDateTime.of(2024, 4, 16, 8, 45),
            LocalDateTime.of(2024, 5, 17, 10, 0),
            LocalDateTime.of(2024, 6, 18, 12, 30),
            LocalDateTime.of(2024, 7, 19, 14, 15),
            LocalDateTime.of(2024, 8, 20, 16, 45)
    };

    private BigDecimal[] arrPrecios = { new BigDecimal("38.13"), new BigDecimal("26.28"), new BigDecimal("77.90"), new BigDecimal("44.87"), new BigDecimal("50.34"), new BigDecimal("45.45"), new BigDecimal("63.24"), new BigDecimal("66.22"), new BigDecimal("107.70"), new BigDecimal("68.00"), new BigDecimal("98.42"), new BigDecimal("82.07"), new BigDecimal("44.70"), new BigDecimal("10.82"), new BigDecimal("853.21"), new BigDecimal("436.71"), new BigDecimal("459.11"), new BigDecimal("579.15"), new BigDecimal("721.65"), new BigDecimal("857.91") };
    
    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            AlquilerEntity oAlquilerEntity = new AlquilerEntity();
            oAlquilerEntity.setInicio(arrFechas[oRandomService.getRandomInt(0, arrFechas.length - 1)]);
            oAlquilerEntity.setFin(arrFechas[oRandomService.getRandomInt(0, arrFechas.length - 1)]);
            oAlquilerEntity.setPrecio(arrPrecios[oRandomService.getRandomInt(0, arrPrecios.length - 1)]);
            oAlquilerEntity.setAlumno(oAlumnoService.randomSelection());
            oAlquilerEntity.setTaquilla(oTaquillaService.randomSelection());
            oAlquilerRepository.save(oAlquilerEntity);
        }
        return oAlquilerRepository.count();
    }

    public Page<AlquilerEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oAlquilerRepository
                    .findByInicioContainingOrFinContaining(
                            filter.get(), filter.get(),
                            oPageable);
        } else {
            return oAlquilerRepository.findAll(oPageable);
        }
    }

    public AlquilerEntity get(Long id) {
        return oAlquilerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alquiler no encontrado"));
        // return oAlquilerRepository.findById(id).get();
    }

    public Long count() {
        return oAlquilerRepository.count();
    }

    public Long delete(Long id) {
        oAlquilerRepository.deleteById(id);
        return 1L;
    }

    public AlquilerEntity create(AlquilerEntity oAlquilerEntity) {
        return oAlquilerRepository.save(oAlquilerEntity);
    }

    public AlquilerEntity update(AlquilerEntity oAlquilerEntity) {
        AlquilerEntity oAlquilerEntityFromDatabase = oAlquilerRepository.findById(oAlquilerEntity.getId()).get();
        if (oAlquilerEntity.getInicio() != null) {
            oAlquilerEntityFromDatabase.setInicio(oAlquilerEntity.getInicio());
        }
        if (oAlquilerEntity.getFin() != null) {
            oAlquilerEntityFromDatabase.setFin(oAlquilerEntity.getFin());
        }
        if (oAlquilerEntity.getPrecio() != null) {
            oAlquilerEntityFromDatabase.setPrecio(oAlquilerEntity.getPrecio());
        }
        return oAlquilerRepository.save(oAlquilerEntityFromDatabase);
    }

    public Long deleteAll() {
        oAlquilerRepository.deleteAll();
        return this.count();
    }

    public AlquilerEntity randomSelection() {
        return oAlquilerRepository.findById((long) oRandomService.getRandomInt(1, (int) (long) this.count())).get();
    }

  /**
     * Actualiza la disponibilidad de las taquillas al iniciar el servidor.
    */
    @Transactional
    public void actualizarDisponibilidadInicial() {
        List<AlquilerEntity> alquileres = oAlquilerRepository.findAll();

        alquileres.forEach(alquiler -> {
            boolean disponible = alquiler.getFin().isBefore(LocalDateTime.now());
            oTaquillaRepository.actualizarDisponibilidad(alquiler.getTaquilla().getId(), disponible);
        });

        System.out.println("Disponibilidad de taquillas actualizada al iniciar el servidor.");
    } 

}