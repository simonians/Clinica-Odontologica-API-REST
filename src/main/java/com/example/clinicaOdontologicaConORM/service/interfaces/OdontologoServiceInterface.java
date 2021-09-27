package com.example.clinicaOdontologicaConORM.service.interfaces;

import com.example.clinicaOdontologicaConORM.dto.OdontologoDTO;
import com.example.clinicaOdontologicaConORM.persistence.entities.Odontologo;

import java.util.Set;

public interface OdontologoServiceInterface extends ServiceInterface<OdontologoDTO>{
    public Set<OdontologoDTO> obtenerOdontologosPorSuApellidoLike(String apellidoOdontolog);
}
