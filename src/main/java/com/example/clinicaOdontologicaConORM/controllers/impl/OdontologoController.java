package com.example.clinicaOdontologicaConORM.controllers.impl;

import com.example.clinicaOdontologicaConORM.controllers.ControllerInterface;
import com.example.clinicaOdontologicaConORM.dto.OdontologoDTO;
import com.example.clinicaOdontologicaConORM.exceptions.BadRequestException;
import com.example.clinicaOdontologicaConORM.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaConORM.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController implements ControllerInterface<OdontologoDTO> {

    @Autowired(required = true)
    OdontologoService service;

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
    public ResponseEntity<List<OdontologoDTO>> consultarTodos() throws ResourceNotFoundException
    {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDeBDD(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.eliminar(id));
    }

    @Override
    @PutMapping()
    public ResponseEntity<String> actualizarEnBDD(@RequestBody OdontologoDTO odontologo) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<String> respuesta;
        if (odontologo.getId() != null){
            respuesta = ResponseEntity.ok(service.actualizar(odontologo));
        } else{
            throw new BadRequestException("Falta el id del odontólogo");
        }
        return respuesta;
    }

    @GetMapping("/apellidos/{apellido}")
    public ResponseEntity<?> obtenerOdontologosPorApellidoEnElPathLike(@PathVariable String apellido){
        return ResponseEntity.ok(service.obtenerOdontologosPorSuApellidoLike(apellido));
    }

    // Lo hacemos con request param para tener otra forma de listarlos
    @GetMapping("/apellidos")
    public ResponseEntity<?> obtenerOdontologosPorApellidoLike(@RequestParam String apellido){
        return ResponseEntity.ok(service.obtenerOdontologosPorSuApellidoLike(apellido));
    }


}
