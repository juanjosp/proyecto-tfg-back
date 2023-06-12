package com.s2daw.demo.services;

import com.s2daw.demo.models.Horario;
import com.s2daw.demo.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;

    @Autowired
    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> getHorarioById(Integer id) {
        return horarioRepository.findById(id);
    }

    public Horario createHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public Horario updateHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public void deleteHorario(Integer id) {
        horarioRepository.deleteById(id);
    }
}
