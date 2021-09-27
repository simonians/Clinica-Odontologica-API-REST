package com.example.clinicaOdontologicaConORM.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface ControllerInterface <E>{
    @PostMapping("/crear") ResponseEntity<?> crearEnBDD(@RequestBody E e);
    @GetMapping("/todos") ResponseEntity<?> consultarTodos();
    @DeleteMapping("/eliminar/{id}") ResponseEntity<?> eliminarDeBDD(@PathVariable Integer id);
    @PutMapping ResponseEntity<?> actualizarEnBDD(@RequestBody E e);
}
