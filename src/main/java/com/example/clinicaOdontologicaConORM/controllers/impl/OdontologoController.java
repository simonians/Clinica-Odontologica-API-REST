package com.example.clinicaOdontologicaConORM.controllers.impl;

import com.example.clinicaOdontologicaConORM.controllers.ControllerInterface;
import com.example.clinicaOdontologicaConORM.persistance.entities.Odontologo;
import com.example.clinicaOdontologicaConORM.services.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController implements ControllerInterface<Odontologo> {

    @Autowired(required = true)
    OdontologoService service;


    @Override
    @PostMapping("/crear")
    public ResponseEntity<Odontologo> crearEnBDD(@RequestBody Odontologo odontologo) {
        ResponseEntity<Odontologo> respuesta = null;
        Odontologo odontologoInsertado = service.insertar(odontologo);
        if (odontologoInsertado != null){
            respuesta = ResponseEntity.ok(odontologoInsertado);
        } else {
            respuesta = ResponseEntity.badRequest().body(odontologo);
        }
        return respuesta;

    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<Odontologo>> consultarTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDeBDD(@PathVariable Integer id) {
        return ResponseEntity.ok(service.eliminar(id));
    }

    @Override
    @PutMapping()
    public ResponseEntity<?> actualizarEnBDD(@RequestBody Odontologo odontologo) {
        if (odontologo.getId() != null)
            return ResponseEntity.ok(service.actualizar(odontologo));
        else
            return ResponseEntity.ok("No se lograron actualizar los datos del odontologo porque falta el id a la hora de realizar la consulta");

    }


}
