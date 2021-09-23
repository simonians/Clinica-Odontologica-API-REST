package com.example.clinicaOdontologicaConORM.service.impl;

import com.example.clinicaOdontologicaConORM.persistence.entities.Paciente;
import com.example.clinicaOdontologicaConORM.persistence.repository.PacienteRepository;
import com.example.clinicaOdontologicaConORM.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements ServiceInterface<Paciente> {
    @Autowired
    PacienteRepository repository;

    @Override
    public Paciente insertar(Paciente entidad){
        Paciente respuesta = repository.save(entidad);
        return respuesta;
    }

    @Override
    public List<Paciente> obtenerTodos() throws Exception{
        List<Paciente> lista = repository.findAll();
        if (lista.size() == 0){
            throw new Exception("No hay pacientes aún cargados en la base de datos");
        }
        return lista;
    }

    @Override
    public String actualizar(Paciente entidad) throws Exception {
        String respuesta;
        Paciente pacienteAModificar = this.buscarPorId(entidad.getId());
        if(pacienteAModificar != null){
            entidad.setFechaIngreso(pacienteAModificar.getFechaIngreso());
            repository.save(entidad);
            respuesta = "Actualización con éxito del paciente con id: " + entidad.getId();
        } else {
            throw new Exception("No se logró actualizar el paciente. El paciente con id " + entidad.getId() +" no fue encontrado en la base de datos");
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
            throw new Exception("No se logró eliminar el paciente de la base de datos. El id " + id +" no fue encontrado en la base de datos.");
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
