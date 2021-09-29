package com.example.clinicaOdontologicaConORM.persistence.repository;

import com.example.clinicaOdontologicaConORM.persistence.entities.Odontologo;
import com.example.clinicaOdontologicaConORM.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {

}
