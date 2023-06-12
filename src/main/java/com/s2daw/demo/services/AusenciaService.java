package com.s2daw.demo.services;


import com.s2daw.demo.models.Ausencia;
import com.s2daw.demo.repositories.AusenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AusenciaService {

    private final AusenciaRepository ausenciaRepository;

    @Autowired
    public AusenciaService(AusenciaRepository ausenciaRepository) {
        this.ausenciaRepository = ausenciaRepository;
    }

    public List<Ausencia> getAllAusencia() {
        return ausenciaRepository.findAll();
    }

    public Optional<Ausencia> getAusenciaById(Integer id) {
        return ausenciaRepository.findById(id);
    }

    public Ausencia createAusencia(Ausencia ausencia) {
        return ausenciaRepository.save(ausencia);
    }

    public Ausencia updateAusencia(Ausencia ausencia) {
        return ausenciaRepository.save(ausencia);
    }

    public void deleteAusencia(Integer id) {
        ausenciaRepository.deleteById(id);
    }
}
