package com.s2daw.demo.controllers;

import com.s2daw.demo.models.Guardia;
import com.s2daw.demo.services.GuardiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guardias")
public class GuardiaController {

    private final GuardiaService guardiaService;

    @Autowired
    public GuardiaController(GuardiaService guardiaService) {
        this.guardiaService = guardiaService;
    }

    @GetMapping
    public List<Guardia> getAllGuardias() {
        return guardiaService.getAllGuardias();
    }

    @GetMapping("/{id}")
    public Optional<Guardia> getGuardiaById(@PathVariable Integer id) {
        return guardiaService.getGuardiaById(id);
    }

    @PostMapping
    public Guardia createGuardia(@RequestBody Guardia guardia) {
        return guardiaService.createGuardia(guardia);
    }

    @PutMapping("/{id}")
    public Guardia updateGuardia(@PathVariable Integer id, @RequestBody Guardia guardia) {
        guardia.setId(id);
        return guardiaService.updateGuardia(guardia);
    }

    @DeleteMapping("/{id}")
    public void deleteGuardia(@PathVariable Integer id) {
        guardiaService.deleteGuardia(id);
    }

    @GetMapping("/profesor/{profesorId}")
    public List<Guardia> obtenerGuardiasPorProfesor(@PathVariable Integer profesorId) {
        return guardiaService.obtenerGuardiasPorProfesor(profesorId);
    }

    @PutMapping("/{guardiaId}/desasignar")
    public void desasignarGuardia(@PathVariable Integer guardiaId) {
        guardiaService.desasignarGuardia(guardiaId);
    }

    @PutMapping("/{guardiaId}/asignar/{profesorId}")
    public void asignarGuardia(@PathVariable Integer guardiaId, @PathVariable Integer profesorId) throws ChangeSetPersister.NotFoundException {
        guardiaService.asignarGuardia(guardiaId, profesorId);
    }
}
