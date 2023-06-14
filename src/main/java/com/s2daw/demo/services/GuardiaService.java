package com.s2daw.demo.services;

import com.s2daw.demo.models.Guardia;
import com.s2daw.demo.models.Profesor;
import com.s2daw.demo.repositories.GuardiaRepository;
import com.s2daw.demo.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class GuardiaService {

    private final GuardiaRepository guardiaRepository;
    private final ProfesorRepository profesorRepository;

    @Autowired
    public GuardiaService(GuardiaRepository guardiaRepository,
                          ProfesorRepository profesorRepository) {
        this.guardiaRepository = guardiaRepository;
        this.profesorRepository = profesorRepository;
    }

    public List<Guardia> getAllGuardias() {
        return guardiaRepository.findAll();
    }

    public Optional<Guardia> getGuardiaById(Integer id) {
        return guardiaRepository.findById(id);
    }

    public Guardia createGuardia(Guardia guardia) {
        return guardiaRepository.save(guardia);
    }

    public Guardia updateGuardia(Guardia guardia) {
        return guardiaRepository.save(guardia);
    }

    public void deleteGuardia(Integer id) {
        guardiaRepository.deleteById(id);
    }
    public List<Guardia> obtenerGuardiasPorProfesor(Integer profesorId) {
        return guardiaRepository.findByProfesorId(profesorId);
    }

    public void desasignarGuardia(Integer guardiaId) {
        Optional<Guardia> guardiaOptional = guardiaRepository.findById(guardiaId);
        if (guardiaOptional.isPresent()) {
            Guardia guardia = guardiaOptional.get();
            guardia.setProfesor(null);
            guardiaRepository.save(guardia);
        }
    }

    public void asignarGuardia(Integer guardiaId, Integer profesorId) throws ChangeSetPersister.NotFoundException {
        Optional<Guardia> guardiaOptional = guardiaRepository.findById(guardiaId);
        if (guardiaOptional.isPresent()) {
            Guardia guardia = guardiaOptional.get();
            Optional<Profesor> profesorOptional = profesorRepository.findById(profesorId);
            if (profesorOptional.isPresent()) {
                Profesor profesor = profesorOptional.get();
                guardia.setProfesor(profesor);
                guardiaRepository.save(guardia);
            } else {
                throw new ChangeSetPersister.NotFoundException();
            }
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }





}
