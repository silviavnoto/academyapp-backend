package net.ausiasmarch.academyapp.api;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
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

import net.ausiasmarch.academyapp.entity.TaquillaEntity;
import net.ausiasmarch.academyapp.service.TaquillaService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/taquilla")
public class Taquilla {

    @Autowired
    TaquillaService oTaquillaService;

    @GetMapping("")
    public ResponseEntity<Page<TaquillaEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<TaquillaEntity>>(oTaquillaService.getPage(oPageable, filter), HttpStatus.OK);
    }
    
     @GetMapping("/{id}")
    public ResponseEntity<TaquillaEntity> getTaquilla(@PathVariable Long id) {
        return new ResponseEntity<TaquillaEntity>(oTaquillaService.get(id), HttpStatus.OK);
    }

     @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oTaquillaService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oTaquillaService.delete(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<TaquillaEntity> create(@RequestBody TaquillaEntity oTaquillaEntity) {
        return new ResponseEntity<TaquillaEntity>(oTaquillaService.create(oTaquillaEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TaquillaEntity> update(@RequestBody TaquillaEntity oTaquillaEntity) {
        return new ResponseEntity<TaquillaEntity>(oTaquillaService.update(oTaquillaEntity), HttpStatus.OK);
    }

    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oTaquillaService.randomCreate(cantidad), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oTaquillaService.deleteAll(), HttpStatus.OK);
    }

    @PutMapping("/flip/{id}")
    public ResponseEntity<TaquillaEntity> flip(@PathVariable Long id) {
        return new ResponseEntity<TaquillaEntity>(oTaquillaService.flip(id), HttpStatus.OK);
    }
}
