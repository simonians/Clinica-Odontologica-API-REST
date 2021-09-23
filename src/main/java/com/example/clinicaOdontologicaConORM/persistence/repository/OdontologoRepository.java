package com.example.clinicaOdontologicaConORM.persistence.repository;

import com.example.clinicaOdontologicaConORM.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
    /*@Query("SELECT o FROM Odontologo where o.matricula=?1")
    Optional<Odontologo> buscarOdontologoPorMatricula(Integer numeroMatricula);*/
}
