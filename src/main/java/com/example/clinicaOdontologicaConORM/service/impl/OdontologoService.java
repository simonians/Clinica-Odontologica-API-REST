package com.example.clinicaOdontologicaConORM.service.impl;

import com.example.clinicaOdontologicaConORM.persistence.entities.Odontologo;
import com.example.clinicaOdontologicaConORM.persistence.repository.OdontologoRepository;
import com.example.clinicaOdontologicaConORM.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements ServiceInterface<Odontologo> {

    @Autowired
    OdontologoRepository repository;


    @Override
    public Odontologo insertar(Odontologo entidad){
        Odontologo respuesta = repository.save(entidad);
        return respuesta;
    }

    @Override
    public List<Odontologo> obtenerTodos() throws Exception{
        List<Odontologo> lista = repository.findAll();
        if (lista.size() == 0){
            throw new Exception("No hay odontólogos aún cargados en la base de datos");
        }
        return lista;
    }

    @Override
    public String actualizar(Odontologo entidad) throws Exception {
        String respuesta;
        Odontologo odontologoAModificar = this.buscarPorId(entidad.getId());
        if(odontologoAModificar != null){
            repository.save(entidad);
            respuesta = "Actualización con éxito del odontólogo con id: " + entidad.getId();
        }else {
            throw new Exception("No se logró actualizar el odontólogo. El odontólogo con id " + entidad.getId() + " no fue encontrado en la base de datos");
        }
        return respuesta;
    }

    @Override
    public String eliminar(Integer id) throws Exception{
        String respuesta;
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            respuesta = "Eliminado con éxito";
        } else {
            throw new Exception("No se logró eliminar el odontologo de la base de datos. El id " + id +" no fue encontrado.");
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
