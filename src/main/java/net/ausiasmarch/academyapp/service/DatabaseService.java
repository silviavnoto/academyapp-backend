package net.ausiasmarch.academyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {


    @Autowired
    ClaseService oClaseService;

    
    public Long fill() {     

        oClaseService.randomCreate(25L);
       // oParticipa.randomCreate(25L);
       // oUsuario.randomCreate(25L);
       // oTipoUsuario.randomCreate(25L);
        return 0L;

    }
}
