package com.example.clinicaOdontologicaConORM.controllers.impl;

import com.example.clinicaOdontologicaConORM.controllers.ControllerInterface;
import com.example.clinicaOdontologicaConORM.dto.OdontologoDTO;
import com.example.clinicaOdontologicaConORM.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController implements ControllerInterface<OdontologoDTO> {

    @Autowired(required = true)
    OdontologoService service;

    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    @Override
    @PostMapping("/crear")
    public ResponseEntity<OdontologoDTO> crearEnBDD(@RequestBody OdontologoDTO odontologo) {
        ResponseEntity<OdontologoDTO> respuesta = ResponseEntity.badRequest().body(odontologo);;
        OdontologoDTO odontologoInsertado = service.insertar(odontologo);
        if (odontologoInsertado != null){
            respuesta = ResponseEntity.ok(odontologoInsertado);
        }

        return respuesta;

    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDTO>> consultarTodos() {
        ResponseEntity<List<OdontologoDTO>> lista = null;
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
    public ResponseEntity<String> actualizarEnBDD(@RequestBody OdontologoDTO odontologo) {
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

    @GetMapping("/apellidos/{apellido}")
    public ResponseEntity<?> obtenerOdontologosPorApellidoEnElPathLike(@PathVariable String apellido){
        ResponseEntity<Set<OdontologoDTO>> listaOdontologs = null;
        try {
            listaOdontologs = ResponseEntity.ok(service.obtenerOdontologosPorSuApellidoLike(apellido));
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return listaOdontologs;
    }

    // Lo hacemos con request param para tener otra forma de listarlos
    @GetMapping("/apellidos")
    public ResponseEntity<?> obtenerOdontologosPorApellidoLike(@RequestParam String apellido){
        ResponseEntity<Set<OdontologoDTO>> listaOdontologs = null;
        try {
            listaOdontologs = ResponseEntity.ok(service.obtenerOdontologosPorSuApellidoLike(apellido));
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return listaOdontologs;
    }


}
