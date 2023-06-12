package com.s2daw.demo.controllers;

import com.s2daw.demo.models.Asignatura;
import com.s2daw.demo.services.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    @Autowired
    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> getAsignaturaById(@PathVariable Integer id) {
        Optional<Asignatura> asignatura = asignaturaService.getAsignaturaById(id);
        return asignatura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asignatura> createAsignatura(@RequestBody Asignatura asignatura) {
        Asignatura nuevaAsignatura = asignaturaService.createAsignatura(asignatura);
        return ResponseEntity.ok(nuevaAsignatura);
    }

    @PutMapping("/{id}")
    public Asignatura updateAsignatura(@PathVariable Integer id, @RequestBody Asignatura asignatura) {
        asignatura.setId(id);
        return asignaturaService.updateAsignatura(asignatura);
    }

    @DeleteMapping("/{id}")
    public void deleteAsignatura(@PathVariable Integer id) {
        asignaturaService.deleteAsignatura(id);
    }

    @GetMapping("/{nombre}/id")
    public ResponseEntity<Integer> obtenerAsignaturaIdPorNombre(@PathVariable String nombre) {
        Integer asignaturaId = asignaturaService.obtenerAsignaturaIdPorNombre(nombre);
        if (asignaturaId != null) {
            return ResponseEntity.ok(asignaturaId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
