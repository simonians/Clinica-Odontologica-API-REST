package com.example.clinicaOdontologicaConORM.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ControllerInterface <E>{
    @PostMapping("/crear") ResponseEntity<E> crearEnBDD(@RequestBody E e);
    @GetMapping("/todos") ResponseEntity<List<E>> consultarTodos();
    @DeleteMapping("/eliminar/{id}") ResponseEntity<?> eliminarDeBDD(@PathVariable Integer id);
    @PutMapping ResponseEntity<?> actualizarEnBDD(@RequestBody E e);
}
