package com.example.clinicaOdontologicaConORM.controllers.impl;

import com.example.clinicaOdontologicaConORM.controllers.ControllerInterface;
import com.example.clinicaOdontologicaConORM.persistance.entities.Paciente;
import com.example.clinicaOdontologicaConORM.services.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController implements ControllerInterface<Paciente> {

    @Autowired
    PacienteService service;


    @Override
    @PostMapping("/crear")
    public ResponseEntity<Paciente> crearEnBDD(@RequestBody Paciente paciente) {
        ResponseEntity<Paciente> response;
        paciente.setFechaIngreso(LocalDate.now());
        Paciente pacienteInsertado = service.insertar(paciente);
        if (pacienteInsertado != null) {
            response = ResponseEntity.ok(pacienteInsertado);
        } else {
            response = ResponseEntity.badRequest().body(paciente);
        }
        return response;
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> consultarTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDeBDD(@PathVariable Integer id) {
        return ResponseEntity.ok(service.eliminar(id));
    }

    @Override
    @PutMapping
    public ResponseEntity<String> actualizarEnBDD(@RequestBody Paciente paciente) {
        if(paciente.getId() != null && paciente.getDomicilio().getId() != null)
            return ResponseEntity.ok(service.actualizar(paciente));
        else
            return ResponseEntity.ok("No se lograron actualizar los datos del paciente porque falta el id del domicilio o del paciente a actualizar");
    }

}
