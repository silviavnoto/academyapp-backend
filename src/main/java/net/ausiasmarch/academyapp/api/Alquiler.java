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

import net.ausiasmarch.academyapp.entity.AlquilerEntity;
import net.ausiasmarch.academyapp.service.AlquilerService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/alquiler")
public class Alquiler {

    @Autowired
    AlquilerService oAlquilerService;
    
    @GetMapping("")
    public ResponseEntity<Page<AlquilerEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<AlquilerEntity>>(oAlquilerService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerEntity> getAlquiler(@PathVariable Long id) {
        return new ResponseEntity<AlquilerEntity>(oAlquilerService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oAlquilerService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oAlquilerService.delete(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<AlquilerEntity> create(@RequestBody AlquilerEntity oAlquilerEntity) {
        return new ResponseEntity<AlquilerEntity>(oAlquilerService.create(oAlquilerEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<AlquilerEntity> update(@RequestBody AlquilerEntity oAlquilerEntity) {
        return new ResponseEntity<AlquilerEntity>(oAlquilerService.update(oAlquilerEntity), HttpStatus.OK);
    }

    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oAlquilerService.randomCreate(cantidad), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oAlquilerService.deleteAll(), HttpStatus.OK);
    }
}
