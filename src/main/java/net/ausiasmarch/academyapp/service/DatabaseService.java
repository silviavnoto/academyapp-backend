package net.ausiasmarch.academyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    AlumnoService oAlumnoService;

    @Autowired
    ProfesorService oProfesorService;

    @Autowired
    ClaseService oClaseService;

    @Autowired
    TaquillaService oTaquillaService;

    @Autowired
    AlquilerService oAlquilerService;
    
    public Long fill() {     
        oAlumnoService.randomCreate(25L);  
        oProfesorService.randomCreate(25L);
        oClaseService.randomCreate(25L);
        oTaquillaService.randomCreate(25L);
        oAlquilerService.randomCreate(25L);
        return 0L;

    }
}
