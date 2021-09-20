package com.example.clinicaOdontologicaConORM.services.impl;

import com.example.clinicaOdontologicaConORM.persistance.entities.Paciente;
import com.example.clinicaOdontologicaConORM.persistance.repository.PacienteRepository;
import com.example.clinicaOdontologicaConORM.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PacienteService implements ServiceInterface<Paciente> {
    @Autowired
    PacienteRepository repository;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PacienteService.class);

    @Override
    public Paciente insertar(Paciente entidad) {
        entidad.setFechaIngreso(LocalDate.now());
        Paciente respuesta = null;
        try{
            respuesta = repository.save(entidad);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public List<Paciente> obtenerTodos() {
        List<Paciente> lista = null;
        try {
            lista = repository.findAll();
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return lista;
    }

    @Override
    public String actualizar(Paciente entidad) {
        String respuesta = "No se ha logrado actualizar el paciente con id: " + entidad.getId() + " porque no se lo encontró en la base de datos";
        try{
            Paciente pacienteAModificar = this.buscarPorId(entidad.getId());
            if(pacienteAModificar != null){
                entidad.setFechaIngreso(pacienteAModificar.getFechaIngreso());
                repository.save(entidad);
                respuesta = "Actualización con éxito del paciente con id: " + entidad.getId();
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public String eliminar(Integer id) {
        String respuesta = "No se logró eliminar el paciente de la base de datos";
        try{
            if(repository.findById(id).isPresent()){
                repository.deleteById(id);
                respuesta = "Eliminado con éxito";
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        Paciente pacienteObtenido = null;
        if(repository.findById(id).isPresent()){
            pacienteObtenido = repository.findById(id).get();
        }
        return pacienteObtenido;
    }
}
