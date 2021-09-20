package com.example.clinicaOdontologicaConORM.persistance.repository;

import com.example.clinicaOdontologicaConORM.persistance.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
