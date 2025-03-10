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

import net.ausiasmarch.academyapp.entity.ClaseEntity;
import net.ausiasmarch.academyapp.entity.ParticipaEntity;
import net.ausiasmarch.academyapp.service.ClaseService;

//@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clase")
public class Clase {

    @Autowired
    ClaseService oClaseService;

    @GetMapping("")
    public ResponseEntity<PageDTO<ClaseEntity>> getPage(
            Pageable oPageable,
            @RequestParam Optional<String> filter) {

        Page<ClaseEntity> page = oClaseService.getPage(oPageable, filter);
        return new ResponseEntity<>(new PageDTO<>(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaseEntity> getClase(@PathVariable Long id) {
        return new ResponseEntity<ClaseEntity>(oClaseService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oClaseService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oClaseService.delete(id), HttpStatus.OK);
    }

    /*
     * @PostMapping("/new")
     * public ResponseEntity<?> create(@RequestBody ClaseEntity oClaseEntity) {
     * if (oClaseEntity.getUsuario().getId() == null ) {
     * throw new DataIntegrityViolationException("id_usuario no puede ser nulo");
     * }
     * return new ResponseEntity<>(oClaseService.create(oClaseEntity),
     * HttpStatus.OK);
     * }
     */

    @PutMapping("")
    public ResponseEntity<ClaseEntity> update(@RequestBody ClaseEntity oClaseEntity) {
        return new ResponseEntity<ClaseEntity>(oClaseService.update(oClaseEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ClaseEntity> create(@RequestBody ClaseEntity oClaseEntity) {
        return new ResponseEntity<ClaseEntity>(oClaseService.create(oClaseEntity), HttpStatus.OK);
    }

    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oClaseService.randomCreate(cantidad), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oClaseService.deleteAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/usuarios")
    public ResponseEntity<Page<ParticipaEntity>> getUsuariosEnClase(
            @PathVariable Long id, Pageable pageable) {
        return new ResponseEntity<>(oClaseService.getUsuariosEnClase(id, pageable), HttpStatus.OK);
    }

}
