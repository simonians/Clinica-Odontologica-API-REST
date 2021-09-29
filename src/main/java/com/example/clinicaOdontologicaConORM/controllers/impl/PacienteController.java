package com.example.clinicaOdontologicaConORM.controllers.impl;

import com.example.clinicaOdontologicaConORM.controllers.ControllerInterface;
import com.example.clinicaOdontologicaConORM.dto.PacienteDTO;
import com.example.clinicaOdontologicaConORM.exceptions.BadRequestException;
import com.example.clinicaOdontologicaConORM.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaConORM.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController implements ControllerInterface<PacienteDTO> {

    @Autowired
    PacienteService service;

    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Override
    @PostMapping("/crear")
    public ResponseEntity<PacienteDTO> crearEnBDD(@RequestBody PacienteDTO paciente) {
        ResponseEntity<PacienteDTO> response = null;
        paciente.setFechaIngreso(LocalDate.now());
        PacienteDTO pacienteInsertado = service.insertar(paciente);
        if (pacienteInsertado != null) {
            response = ResponseEntity.ok(pacienteInsertado);
        }

        return response;
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDTO>> consultarTodos() throws BadRequestException {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDeBDD(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.eliminar(id));
    }

    @Override
    @PutMapping
    public ResponseEntity<String> actualizarEnBDD(@RequestBody PacienteDTO paciente) throws BadRequestException,ResourceNotFoundException {
        ResponseEntity<String> respuesta;
        if(paciente.getId() != null ){
            respuesta = ResponseEntity.ok(service.actualizar(paciente));
        } else {
            throw new BadRequestException("Id del paciente o del domicilio faltantes");
        }
        return respuesta;
    }

}
