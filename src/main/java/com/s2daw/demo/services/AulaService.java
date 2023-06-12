package com.s2daw.demo.services;

import com.s2daw.demo.models.Aula;
import com.s2daw.demo.repositories.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AulaService {

    private final AulaRepository aulaRepository;

    @Autowired
    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    public List<Aula> getAllAulas() {
        return aulaRepository.findAll();
    }

    public Optional<Aula> getAulaById(Integer id) {
        return aulaRepository.findById(id);
    }

    public Aula createAula(Aula aula) {
        return aulaRepository.save(aula);
    }

    public Aula updateAula(Aula aula) {
        return aulaRepository.save(aula);
    }

    public void deleteAula(Integer id) {
        aulaRepository.deleteById(id);
    }

    public Optional<Aula> getAulaByNombre(String nombre) {
        return aulaRepository.findByNombre(nombre);
    }
}
