package net.ausiasmarch.academyapp.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.academyapp.entity.ProfesorEntity;
import net.ausiasmarch.academyapp.service.ProfesorService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/profesor")
public class Profesor {

    @Autowired
    ProfesorService oProfesorService;
    
    @GetMapping("")
    public ResponseEntity<Page<ProfesorEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<ProfesorEntity>>(oProfesorService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorEntity> getProfesor(@PathVariable Long id) {
        return new ResponseEntity<ProfesorEntity>(oProfesorService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oProfesorService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oProfesorService.delete(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<ProfesorEntity> create(@RequestBody ProfesorEntity oProfesorEntity) {
        return new ResponseEntity<ProfesorEntity>(oProfesorService.create(oProfesorEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProfesorEntity> update(@RequestBody ProfesorEntity oProfesorEntity) {
        return new ResponseEntity<ProfesorEntity>(oProfesorService.update(oProfesorEntity), HttpStatus.OK);
    }

    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oProfesorService.randomCreate(cantidad), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oProfesorService.deleteAll(), HttpStatus.OK);
    }
}
