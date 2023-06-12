package com.s2daw.demo.services;

import com.s2daw.demo.models.Asignatura;
import com.s2daw.demo.repositories.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;

    @Autowired
    public AsignaturaService(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public Optional<Asignatura> getAsignaturaById(Integer id) {
        return asignaturaRepository.findById(id);
    }

    public Asignatura createAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public Asignatura updateAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public void deleteAsignatura(Integer id) {
        asignaturaRepository.deleteById(id);
    }

    public Integer obtenerAsignaturaIdPorNombre(String nombre) {
        Optional<Asignatura> asignatura = asignaturaRepository.findByNombre(nombre);
        return asignatura.map(Asignatura::getId).orElse(null);
    }
}
