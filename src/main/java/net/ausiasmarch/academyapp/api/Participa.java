package net.ausiasmarch.academyapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.ausiasmarch.academyapp.entity.ParticipaEntity;
import net.ausiasmarch.academyapp.service.ParticipaService;

import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/participa")
public class Participa {

    @Autowired
    ParticipaService oParticipaService;

    @GetMapping("")
    public ResponseEntity<Page<ParticipaEntity>> getPage(
        Pageable pageable) {
        return new ResponseEntity<>(oParticipaService.getPage(pageable, Optional.empty()), HttpStatus.OK);
    }

    @GetMapping("/xusuario/{id}")
    public ResponseEntity<Page<ParticipaEntity>> getByUsuario(Pageable pageable, @PathVariable Long id) {
        return new ResponseEntity<>(oParticipaService.getByUsuario(pageable, id), HttpStatus.OK);
    }

    @GetMapping("/xclase/{id}")
    public ResponseEntity<Page<ParticipaEntity>> getByClase(Pageable pageable, @PathVariable Long id) {
        return new ResponseEntity<>(oParticipaService.getByClase(pageable, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipaEntity> getParticipa(@PathVariable Long id) {
        return new ResponseEntity<>(oParticipaService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(oParticipaService.count(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ParticipaEntity> create(@RequestBody ParticipaEntity participaEntity) {
        return new ResponseEntity<>(oParticipaService.create(participaEntity), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<ParticipaEntity> update(@RequestBody ParticipaEntity participaEntity) {
        return new ResponseEntity<>(oParticipaService.update(participaEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<>(oParticipaService.delete(id), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<>(oParticipaService.deleteAll(), HttpStatus.OK);
    }
}
