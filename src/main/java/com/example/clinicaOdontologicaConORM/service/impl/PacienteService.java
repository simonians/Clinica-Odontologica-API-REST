package com.example.clinicaOdontologicaConORM.service.impl;

import com.example.clinicaOdontologicaConORM.config.SpringConfig;
import com.example.clinicaOdontologicaConORM.dto.DomicilioDTO;
import com.example.clinicaOdontologicaConORM.dto.PacienteDTO;
import com.example.clinicaOdontologicaConORM.persistence.entities.Domicilio;
import com.example.clinicaOdontologicaConORM.persistence.entities.Paciente;
import com.example.clinicaOdontologicaConORM.persistence.repository.PacienteRepository;
import com.example.clinicaOdontologicaConORM.service.interfaces.PacienteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements PacienteServiceInterface {
    @Autowired
    PacienteRepository repository;

    @Autowired
    SpringConfig mapper;

    @Autowired
    DomicilioService domicilioService;

    @Override
    public PacienteDTO insertar(PacienteDTO entidad){
        Paciente paciente = mapper.getModelMapper().map(entidad, Paciente.class);
        repository.save(paciente);
        return mapper.getModelMapper().map(paciente, PacienteDTO.class);
    }


    @Override
    public List<PacienteDTO> obtenerTodos() throws Exception{
        List<Paciente> lista = repository.findAll();
        if (lista.size() == 0){
            throw new Exception("No hay pacientes aún cargados en la base de datos");
        }
        return mapper.getModelMapper().map(lista, List.class);
    }

    @Override
    public String actualizar(PacienteDTO entidad) throws Exception {
        String respuesta;
        Optional<Paciente> pacienteAModificar = repository.findById(entidad.getId());
        if(pacienteAModificar.isPresent()){
            repository.save(this.actualizarEnBDD(pacienteAModificar.get(), entidad));
            respuesta = "Actualización con éxito del paciente con id: " + entidad.getId();
        } else {
            throw new Exception("No se logró actualizar el paciente. El paciente con id " + entidad.getId() +" no fue encontrado en la base de datos");
        }
        return respuesta;
    }

    private Paciente actualizarEnBDD(Paciente paciente, PacienteDTO pacienteDto) throws Exception {
        if (pacienteDto.getNombre() != null) {
            paciente.setNombre(pacienteDto.getNombre());
        }
        if (pacienteDto.getApellido() != null) {
            paciente.setApellido(pacienteDto.getApellido());
        }
        if (pacienteDto.getDni() != null) {
            paciente.setDni(pacienteDto.getDni());
        }
        if (pacienteDto.getDomicilio() != null) {
            DomicilioDTO domicilioActualizado = domicilioService.actualizar(pacienteDto.getDomicilio());
            paciente.setDomicilio(mapper.getModelMapper().map(domicilioActualizado, Domicilio.class));
        }
        return paciente;
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

    public PacienteDTO buscarPorId(Integer id){
        return mapper.getModelMapper().map(repository.findById(id).get(), PacienteDTO.class);
    }


}
