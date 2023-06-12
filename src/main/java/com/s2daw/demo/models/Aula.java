package com.s2daw.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "aula")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "capacidad")
    private Integer capacidad;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Aula aula = (Aula) o;
        return id != null && Objects.equals(id, aula.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}