package com.example.clinicaOdontologicaConORM.persistance.repository;

import com.example.clinicaOdontologicaConORM.persistance.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
    /*@Query("SELECT o FROM Odontologo where o.matricula=?1")
    Optional<Odontologo> buscarOdontologoPorMatricula(Integer numeroMatricula);*/
}
