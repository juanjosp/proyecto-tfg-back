package com.s2daw.demo.repositories;

import com.s2daw.demo.models.Ausencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AusenciaRepository extends JpaRepository<Ausencia, Integer> {
}
