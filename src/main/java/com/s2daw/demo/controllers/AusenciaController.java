package com.s2daw.demo.controllers;


import com.s2daw.demo.models.Ausencia;
import com.s2daw.demo.services.AusenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ausencias")
public class AusenciaController {

    private final AusenciaService ausenciaService;

    @Autowired
    public AusenciaController(AusenciaService ausenciaService) {
        this.ausenciaService = ausenciaService;
    }

    @GetMapping
    public List<Ausencia> getAllAusencias() {
        return ausenciaService.getAllAusencia();
    }

    @GetMapping("/{id}")
    public Optional<Ausencia> getAusenciaById(@PathVariable Integer id) {
        return ausenciaService.getAusenciaById(id);
    }

    @PostMapping
    public Ausencia createAusencia(@RequestBody Ausencia ausencia) {
        return ausenciaService.createAusencia(ausencia);
    }

    @PutMapping("/{id}")
    public Ausencia updateAusencia(@PathVariable Integer id, @RequestBody Ausencia ausencia) {
        ausencia.setId(id);
        return ausenciaService.updateAusencia(ausencia);
    }

    @DeleteMapping("/{id}")
    public void deleteAsignatura(@PathVariable Integer id) {
        ausenciaService.deleteAusencia(id);
    }
}
