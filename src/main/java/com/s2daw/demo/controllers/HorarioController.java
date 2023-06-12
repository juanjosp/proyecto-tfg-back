package com.s2daw.demo.controllers;

import com.s2daw.demo.models.Horario;
import com.s2daw.demo.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    private final HorarioService horarioService;

    @Autowired
    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping
    public List<Horario> getAllHorarios() {
        return horarioService.getAllHorarios();
    }

    @GetMapping("/{id}")
    public Optional<Horario> getHorarioById(@PathVariable Integer id) {
        return horarioService.getHorarioById(id);
    }

    @PostMapping
    public Horario createHorario(@RequestBody Horario horario) {
        return horarioService.createHorario(horario);
    }

    @PutMapping("/{id}")
    public Horario updateHorario(@PathVariable Integer id, @RequestBody Horario horario) {
        return horarioService.updateHorario(horario);
    }

    @DeleteMapping("/{id}")
    public void deleteHorario(@PathVariable Integer id) {
        horarioService.deleteHorario(id);
    }
}
