package com.example.clinicaOdontologicaConORM.controllers.impl;

import com.example.clinicaOdontologicaConORM.controllers.ControllerInterface;
import com.example.clinicaOdontologicaConORM.dto.TurnoDTO;
import com.example.clinicaOdontologicaConORM.service.impl.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController implements ControllerInterface<TurnoDTO> {

    @Autowired(required = true)
    TurnoService service;

    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    @Override
    @PostMapping("/crear")
    public ResponseEntity<?> crearEnBDD(@RequestBody TurnoDTO turnoDTO){
        ResponseEntity<?> respuesta = new ResponseEntity("No fue posible crear el turno", HttpStatus.BAD_REQUEST);
        try{
            TurnoDTO turnoCreado = service.insertar(turnoDTO);
            if (turnoCreado != null){
                respuesta = ResponseEntity.ok(turnoCreado);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<?> consultarTodos() {
        ResponseEntity<?> respuesta = new ResponseEntity<>("No fue posible listar todos los turnos", HttpStatus.BAD_REQUEST);
        try{
            List<TurnoDTO> turnos = service.obtenerTodos();
            respuesta = ResponseEntity.ok(turnos);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDeBDD(@PathVariable Integer id) {
        ResponseEntity<?> respuesta = new ResponseEntity<>("No fue posible eliminar el turno", HttpStatus.BAD_REQUEST);
        try{
            String mensajeDeEliminacion = service.eliminar(id);
            if (mensajeDeEliminacion != null){
                respuesta = ResponseEntity.ok(mensajeDeEliminacion);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }

    @Override
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarEnBDD(@RequestBody TurnoDTO turnoDTO) {
        ResponseEntity<String> respuesta = new ResponseEntity<>("No fue posible actualizar los datos del turno", HttpStatus.BAD_REQUEST);
        try {
            String mensajeRespuesta = service.actualizar(turnoDTO);
            if (mensajeRespuesta != null){
                respuesta = ResponseEntity.ok(mensajeRespuesta);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }
}
