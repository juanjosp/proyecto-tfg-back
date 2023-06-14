package com.s2daw.demo.controllers;

import com.s2daw.demo.models.Profesor;
import com.s2daw.demo.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @Autowired
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> getAllProfesores() {
        List<Profesor> profesores = profesorService.getAllProfesores();
        return ResponseEntity.ok(profesores);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable Integer id) {
        Optional<Profesor> profesor = profesorService.getProfesorById(id);
        return profesor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/registro")
    public ResponseEntity<Profesor> registrarProfesor(@RequestBody Profesor profesor) {
        Profesor nuevoProfesor = profesorService.registrarProfesor(profesor);
        return ResponseEntity.ok(nuevoProfesor);
    }

    @PutMapping("/{id}")
    public Profesor updateProfesor(@PathVariable Long id, @RequestBody Profesor profesor) {
        profesor.setId(Math.toIntExact(id));
        return profesorService.updateProfesor(profesor);
    }

    @DeleteMapping("/{id}")
    public void deleteProfesor(@PathVariable Long id) {
        profesorService.deleteProfesor(Math.toIntExact(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Profesor> getProfesorByEmail(@PathVariable String email) {
        Optional<Profesor> profesor = profesorService.getProfesorByEmail(email);
        return profesor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }




}
