package com.example.clinicaOdontologicaConORM.service.impl;

import com.example.clinicaOdontologicaConORM.config.SpringConfig;
import com.example.clinicaOdontologicaConORM.dto.DomicilioDTO;
import com.example.clinicaOdontologicaConORM.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaConORM.persistence.entities.Domicilio;
import com.example.clinicaOdontologicaConORM.persistence.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DomicilioService {
    @Autowired
    DomicilioRepository repository;

    @Autowired
    SpringConfig mapper;

    public DomicilioDTO actualizar(DomicilioDTO domicilioDto) throws ResourceNotFoundException {
        Optional<Domicilio> domicilioEnBD = repository.findById(domicilioDto.getId());
        if (domicilioEnBD.isPresent()) {
            Domicilio actualizado = this.actualizar(domicilioEnBD.get(), domicilioDto);
            Domicilio guardado = repository.save(actualizado);
            return mapper.getModelMapper().map(guardado, DomicilioDTO.class);
        }
        else {
            throw new ResourceNotFoundException("El domicilio con id " + domicilioDto.getId() +" no fue encontrado en la base de datos");
        }
    }

    private Domicilio actualizar(Domicilio domicilio, DomicilioDTO domicilioDto) {
        if (domicilioDto.getCalle() != null) {
            domicilio.setCalle(domicilioDto.getCalle());
        }
        if (domicilioDto.getNumero() != null) {
            domicilio.setNumero(domicilioDto.getNumero());
        }
        if (domicilioDto.getLocalidad() != null) {
            domicilio.setLocalidad(domicilioDto.getLocalidad());
        }
        if (domicilioDto.getProvincia() != null) {
            domicilio.setProvincia(domicilioDto.getProvincia());
        }
        return domicilio;
    }

}
