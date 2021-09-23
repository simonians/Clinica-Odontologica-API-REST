package com.example.clinicaOdontologicaConORM.controllers.impl;

import com.example.clinicaOdontologicaConORM.controllers.ControllerInterface;
import com.example.clinicaOdontologicaConORM.persistence.entities.Odontologo;
import com.example.clinicaOdontologicaConORM.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;


import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController implements ControllerInterface<Odontologo> {

    @Autowired(required = true)
    OdontologoService service;

    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    @Override
    @PostMapping("/crear")
    public ResponseEntity<Odontologo> crearEnBDD(@RequestBody Odontologo odontologo) {
        ResponseEntity<Odontologo> respuesta = ResponseEntity.badRequest().body(odontologo);;
        Odontologo odontologoInsertado = service.insertar(odontologo);
        if (odontologoInsertado != null){
            respuesta = ResponseEntity.ok(odontologoInsertado);
        }

        return respuesta;

    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<Odontologo>> consultarTodos() {
        ResponseEntity<List<Odontologo>> lista = null;
        try {
            lista = ResponseEntity.ok(service.obtenerTodos());
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return lista;
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDeBDD(@PathVariable Integer id) {
        ResponseEntity<String> respuesta = ResponseEntity.ok("No existe ningún odontólogo con el id" + id);
        try {
            respuesta = ResponseEntity.ok(service.eliminar(id));
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }

    @Override
    @PutMapping()
    public ResponseEntity<String> actualizarEnBDD(@RequestBody Odontologo odontologo) {
        ResponseEntity<String> respuesta = ResponseEntity.ok("No se lograron actualizar los datos del odontologo");
        try {
            if (odontologo.getId() != null){
                respuesta = ResponseEntity.ok(service.actualizar(odontologo));
            } else{
                throw new Exception("Falta el id del odontólogo");
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }


}
