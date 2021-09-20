package com.example.clinicaOdontologicaConORM.persistance.repository;

import com.example.clinicaOdontologicaConORM.persistance.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {
}
