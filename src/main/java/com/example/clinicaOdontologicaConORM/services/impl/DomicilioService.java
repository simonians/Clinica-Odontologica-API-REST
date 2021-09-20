package com.example.clinicaOdontologicaConORM.services.impl;

import com.example.clinicaOdontologicaConORM.persistance.entities.Domicilio;
import com.example.clinicaOdontologicaConORM.persistance.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomicilioService {

    @Autowired
    DomicilioRepository repository;

    public List<Domicilio> obtenerTodos() {
        List<Domicilio> domicilios = repository.findAll();
        return domicilios;
    }

}
