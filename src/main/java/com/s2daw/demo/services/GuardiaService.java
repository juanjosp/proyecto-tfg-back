package com.s2daw.demo.services;

import com.s2daw.demo.models.Guardia;
import com.s2daw.demo.models.Horario;
import com.s2daw.demo.models.Profesor;
import com.s2daw.demo.repositories.GuardiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;


import java.util.List;
import java.util.Optional;
@Service
public class GuardiaService {

    private final GuardiaRepository guardiaRepository;

    @Autowired
    public GuardiaService(GuardiaRepository guardiaRepository) {
        this.guardiaRepository = guardiaRepository;
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

    public void asignarGuardia(Integer guardiaId, Integer profesorId) throws NotFoundException {
        Guardia guardia = guardiaRepository.findById(guardiaId).orElseThrow(NotFoundException::new);
        // Realizar la lógica para asignar la guardia al profesor con el ID proporcionado
        Profesor profesor = new Profesor();
        profesor.setId(profesorId);
        guardia.setProfesor(profesor);
        guardiaRepository.save(guardia);
    }

    public void desasignarGuardia(Integer guardiaId) throws NotFoundException {
        Guardia guardia = guardiaRepository.findById(guardiaId).orElseThrow(NotFoundException::new);
        // Realizar la lógica para desasignar la guardia
        guardia.setProfesor(null);
        guardiaRepository.save(guardia);
    }
    public void actualizarProfesorIdEnGuardia(Integer guardiaId, Integer profesorId) throws NotFoundException {
        Optional<Guardia> optionalGuardia = guardiaRepository.findById(guardiaId);
        if (optionalGuardia.isPresent()) {
            Guardia guardia = optionalGuardia.get();
            Profesor profesor = new Profesor();
            profesor.setId(profesorId);
            guardia.setProfesor(profesor);
            guardiaRepository.save(guardia);
        } else {
            throw new NotFoundException();
        }
    }

    public void actualizarProfesorIdEnHorario(Integer guardiaId, Integer profesorId) throws NotFoundException {
        Optional<Guardia> optionalGuardia = guardiaRepository.findById(guardiaId);
        if (optionalGuardia.isPresent()) {
            Guardia guardia = optionalGuardia.get();
            Horario horario = guardia.getHorario();
            Profesor profesor = new Profesor();
            profesor.setId(profesorId);
            horario.setProfesor(profesor);
            guardiaRepository.save(guardia);
        } else {
            throw new NotFoundException();
        }
    }

}
