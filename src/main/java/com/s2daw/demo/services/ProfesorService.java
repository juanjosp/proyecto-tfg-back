package com.s2daw.demo.services;



import com.s2daw.demo.models.Profesor;
import com.s2daw.demo.repositories.ProfesorRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service

public class ProfesorService {

    private final ProfesorRepository profesorRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAll();
    }

    public Optional<Profesor> getProfesorById(Integer id) {
        return profesorRepository.findById(id);
    }


    public Profesor updateProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public void deleteProfesor(Integer id) {
        profesorRepository.deleteById(id);
    }
    @Transactional

    public Profesor registrarProfesor(Profesor profesor) {
        String email = profesor.getEmail();

        // Verificar si el profesor ya existe
        Profesor existentProfesor = profesorRepository.findByEmail(email);
        if (existentProfesor != null) {
            // El profesor ya está registrado, puedes manejar esto devolviendo un valor indicativo o lanzando una excepción
            // Por ejemplo, puedes lanzar una excepción personalizada
            throw new IllegalArgumentException("El profesor ya está registrado");
        }

        // Encriptar la contraseña utilizando Argon2
        String password = profesor.getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashedPassword = argon2.hash(10, 65536, 1, password.getBytes());

        profesor.setPassword(hashedPassword);
        return profesorRepository.save(profesor);
    }


    public boolean obtenerProfesorPorCredenciales(Profesor profesor) {
        String query = "FROM Profesor WHERE email=:email";
        List<Profesor> lista = entityManager.createQuery(query, Profesor.class)
                .setParameter("email", profesor.getEmail())
                .getResultList();
        if (lista.isEmpty()) return false;
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.verify(lista.get(0).getPassword(),profesor.getPassword().getBytes());
    }
    public Optional<Profesor> getProfesorByEmail(String email) {
        return Optional.ofNullable(profesorRepository.findByEmail(email));
    }


    }


