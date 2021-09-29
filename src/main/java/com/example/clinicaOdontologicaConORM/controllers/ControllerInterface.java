package com.example.clinicaOdontologicaConORM.controllers;

import com.example.clinicaOdontologicaConORM.exceptions.BadRequestException;
import com.example.clinicaOdontologicaConORM.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface ControllerInterface <E>{
    @PostMapping("/crear") ResponseEntity<?> crearEnBDD(@RequestBody E e) throws BadRequestException, ResourceNotFoundException;
    @GetMapping("/todos") ResponseEntity<?> consultarTodos() throws ResourceNotFoundException, BadRequestException;
    @DeleteMapping("/eliminar/{id}") ResponseEntity<?> eliminarDeBDD(@PathVariable Integer id) throws Exception;
    @PutMapping ResponseEntity<?> actualizarEnBDD(@RequestBody E e) throws BadRequestException, ResourceNotFoundException;
}
