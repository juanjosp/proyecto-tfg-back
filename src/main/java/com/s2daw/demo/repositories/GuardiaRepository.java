package com.s2daw.demo.repositories;

import com.s2daw.demo.models.Guardia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuardiaRepository extends JpaRepository<Guardia, Integer> {
    List<Guardia> findByProfesorId(Integer profesorId);
}

