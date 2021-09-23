package com.example.clinicaOdontologicaConORM.persistence.repository;

import com.example.clinicaOdontologicaConORM.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
