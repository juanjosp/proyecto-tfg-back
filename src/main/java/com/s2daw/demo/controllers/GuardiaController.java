package com.s2daw.demo.controllers;

import com.s2daw.demo.models.Guardia;
import com.s2daw.demo.services.GuardiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{guardiaId}/asignar")
    public ResponseEntity<String> asignarGuardia(@PathVariable Integer guardiaId, @RequestBody Guardia request) {
        try {
            guardiaService.asignarGuardia(guardiaId, request.getProfesor().getId());
            return ResponseEntity.ok("Guardia asignada correctamente");
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al asignar la guardia");
        }
    }

    @PutMapping("/{guardiaId}/desasignar")
    public ResponseEntity<String> desasignarGuardia(@PathVariable Integer guardiaId) {
        try {
            guardiaService.desasignarGuardia(guardiaId);
            return ResponseEntity.ok("Guardia desasignada correctamente");
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al desasignar la guardia");
        }
    }
    @PutMapping("/{guardiaId}/horario/{profesorId}")
    public ResponseEntity<String> actualizarProfesorIdEnHorario(@PathVariable Integer guardiaId, @PathVariable Integer profesorId) {
        try {
            guardiaService.actualizarProfesorIdEnHorario(guardiaId, profesorId);
            return ResponseEntity.ok("ID de profesor actualizado correctamente en el horario");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el ID de profesor en el horario");
        }
    }
    @PutMapping("/{guardiaId}/profesor/{profesorId}")
    public ResponseEntity<String> actualizarProfesorIdEnGuardia(@PathVariable Integer guardiaId, @PathVariable Integer profesorId) {
        try {
            guardiaService.actualizarProfesorIdEnGuardia(guardiaId, profesorId);
            return ResponseEntity.ok("ID de profesor actualizado correctamente en la guardia");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el ID de profesor en la guardia");
        }
    }

}
