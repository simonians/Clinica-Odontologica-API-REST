package com.example.clinicaOdontologicaConORM.persistence.repository;

import com.example.clinicaOdontologicaConORM.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
    /*@Query("SELECT o FROM Odontologo where o.matricula=?1")
    Optional<Odontologo> buscarOdontologoPorMatricula(Integer numeroMatricula);*/

    @Query("from Odontologo o where o.apellido like %:apellido%")
    Set<Odontologo> getOdontologoByApellidoLike(@Param("apellido")String apellidoOdontologo);
}
