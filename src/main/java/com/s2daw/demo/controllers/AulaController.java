package com.s2daw.demo.controllers;

import com.s2daw.demo.models.Aula;
import com.s2daw.demo.services.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    @Autowired
    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping
    public List<Aula> getAllAulas() {
        return aulaService.getAllAulas();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public Optional<Aula> getAulaById(@PathVariable Integer id) {
        return aulaService.getAulaById(id);
    }

    @GetMapping("/{nombre}/id")
    public ResponseEntity<Integer> getAulaIdByNombre(@PathVariable String nombre) {
        Optional<Aula> aula = aulaService.getAulaByNombre(nombre);
        return aula.map(a -> ResponseEntity.ok(a.getId()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Aula createAula(@RequestBody Aula aula) {
        return aulaService.createAula(aula);
    }

    @PutMapping("/{id}")
    public Aula updateAula(@PathVariable Integer id, @RequestBody Aula aula) {
        aula.setId(id);
        return aulaService.updateAula(aula);
    }

    @DeleteMapping("/{id}")
    public void deleteAula(@PathVariable Integer id) {
        aulaService.deleteAula(id);
    }
}
