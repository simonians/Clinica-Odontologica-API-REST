package com.example.clinicaOdontologicaConORM.controllers.impl;

import com.example.clinicaOdontologicaConORM.controllers.ControllerInterface;
import com.example.clinicaOdontologicaConORM.dto.TurnoDTO;
import com.example.clinicaOdontologicaConORM.exceptions.BadRequestException;
import com.example.clinicaOdontologicaConORM.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaConORM.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/turnos")
public class TurnoController implements ControllerInterface<TurnoDTO> {

    @Autowired(required = true)
    TurnoService service;

    @Override
    @PostMapping("/crear")
    public ResponseEntity<?> crearEnBDD(@RequestBody TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(service.insertar(turnoDTO));
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<?> consultarTodos() throws ResourceNotFoundException {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDeBDD(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.eliminar(id));
    }

    @Override
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarEnBDD(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException, BadRequestException{
        return ResponseEntity.ok(service.actualizar(turnoDTO));
    }

    @GetMapping("/proximaSemana")
    public ResponseEntity<?> turnosProximaSemana() throws ResourceNotFoundException {
        return ResponseEntity.ok(service.turnosProxSemana());
    }
}
