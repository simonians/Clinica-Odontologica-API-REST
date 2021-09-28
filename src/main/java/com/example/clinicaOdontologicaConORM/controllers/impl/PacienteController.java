package com.example.clinicaOdontologicaConORM.controllers.impl;

import com.example.clinicaOdontologicaConORM.controllers.ControllerInterface;
import com.example.clinicaOdontologicaConORM.dto.PacienteDTO;
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
    public ResponseEntity<List<PacienteDTO>> consultarTodos() {
        ResponseEntity<List<PacienteDTO>> lista = null;
        try{
            lista = ResponseEntity.ok(service.obtenerTodos());
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return lista;
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDeBDD(@PathVariable Integer id) {
        ResponseEntity<String> respuesta = null;
        try{
            respuesta = ResponseEntity.ok(service.eliminar(id));
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }

    @Override
    @PutMapping
    public ResponseEntity<String> actualizarEnBDD(@RequestBody PacienteDTO paciente) {
        ResponseEntity<String> respuesta = ResponseEntity.ok("No se lograron actualizar los datos del paciente");
        try {
            if(paciente.getId() != null /*&& paciente.getDomicilio().getId() != null*/){ //HACER VERIFICACION EN EL SERVICE
                respuesta = ResponseEntity.ok(service.actualizar(paciente));
            } else {
                throw new Exception("Id del paciente o del domicilio faltantes");
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }

}
