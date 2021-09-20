package com.example.clinicaOdontologicaConORM.controllers.impl;

import com.example.clinicaOdontologicaConORM.persistance.entities.Domicilio;
import com.example.clinicaOdontologicaConORM.services.impl.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    DomicilioService service;

    @GetMapping("/todos")
    public ResponseEntity<List<Domicilio>> consultarTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

}
