package com.example.clinicaOdontologicaConORM.services.impl;

import com.example.clinicaOdontologicaConORM.persistance.entities.Odontologo;
import com.example.clinicaOdontologicaConORM.persistance.repository.OdontologoRepository;
import com.example.clinicaOdontologicaConORM.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements ServiceInterface<Odontologo> {

    @Autowired
    OdontologoRepository repository;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OdontologoService.class);

    @Override
    public Odontologo insertar(Odontologo entidad) {
        Odontologo respuesta = null;
        try{
            respuesta = repository.save(entidad);
        } catch (Exception e){
            logger.debug(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public List<Odontologo> obtenerTodos() {
        List<Odontologo> lista = null;
        try {
            lista = repository.findAll();
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return lista;
    }

    @Override
    public String actualizar(Odontologo entidad) {
        String respuesta = "No se ha logrado actualizar el odontologo con id: " + entidad.getId() + " porque no se lo encontró en la base de datos";
        try{
            Odontologo odontologoAModificar = this.buscarPorId(entidad.getId());
            if(odontologoAModificar != null){
                repository.save(entidad);
                respuesta = "Actualización con éxito del odontólogo con id: " + entidad.getId();
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public String eliminar(Integer id) {
        String respuesta = "No se logró eliminar el odontólogo de la base de datos";
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
    public Odontologo buscarPorId(Integer id) {
        Odontologo odontologoObtenido = null;
        if(repository.findById(id).isPresent()){
            odontologoObtenido = repository.findById(id).get();
        }
        return odontologoObtenido;
    }

}
