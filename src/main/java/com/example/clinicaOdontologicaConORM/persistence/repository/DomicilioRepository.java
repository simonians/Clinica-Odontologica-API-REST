package com.example.clinicaOdontologicaConORM.persistence.repository;

import com.example.clinicaOdontologicaConORM.persistence.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {
}
