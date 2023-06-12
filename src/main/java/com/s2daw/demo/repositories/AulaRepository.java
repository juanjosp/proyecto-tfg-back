package com.s2daw.demo.repositories;

import com.s2daw.demo.models.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
    Optional<Aula> findByNombre(String nombre);
}
