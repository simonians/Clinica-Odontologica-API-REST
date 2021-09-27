package com.example.clinicaOdontologicaConORM.service.impl;

import com.example.clinicaOdontologicaConORM.config.SpringConfig;
import com.example.clinicaOdontologicaConORM.dto.OdontologoDTO;
import com.example.clinicaOdontologicaConORM.dto.PacienteDTO;
import com.example.clinicaOdontologicaConORM.dto.TurnoDTO;
import com.example.clinicaOdontologicaConORM.persistence.entities.Turno;
import com.example.clinicaOdontologicaConORM.persistence.repository.TurnoRepository;
import com.example.clinicaOdontologicaConORM.service.interfaces.TurnoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements TurnoServiceInterface {
    @Autowired
    TurnoRepository repository;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    SpringConfig mapper;


    @Override
    public TurnoDTO insertar(TurnoDTO entidad) {
        TurnoDTO respuesta = null;
        if (entidad.getPaciente().getId() != null && entidad.getOdontologo() != null){
            PacienteDTO pacienteBuscado = pacienteService.buscarPorId(entidad.getPaciente().getId());
            OdontologoDTO odontologoBuscado = odontologoService.buscarPorId(entidad.getOdontologo().getId());
            if (pacienteBuscado != null && odontologoBuscado !=null){
                //Creamos en BDD
                Turno turno = mapper.getModelMapper().map(entidad, Turno.class);
                respuesta = mapper.getModelMapper().map(repository.save(turno), TurnoDTO.class);

                //Setemos el odntologo y paciente al JSON de la respuesta
                respuesta.setPaciente(pacienteBuscado);
                respuesta.setOdontologo(odontologoBuscado);
            }
        }
        return respuesta;
    }

    @Override
    public List<TurnoDTO> obtenerTodos() throws Exception {
        List<TurnoDTO> respuesta = mapper.getModelMapper().map(repository.findAll(), List.class);
        return respuesta;
    }

    @Override
    public String actualizar(TurnoDTO entidad) throws Exception {
        String respuesta = null;
        Optional<Turno> turnoAModificar = repository.findById(entidad.getId());
        if (turnoAModificar.isPresent()){
            repository.save(this.actualizarEnBDD(turnoAModificar.get(), entidad));
            respuesta = "Actualización con éxito del turno número: " + entidad.getId();
        }
        return respuesta;
    }

    private Turno actualizarEnBDD(Turno turno, TurnoDTO turnoDTO){
        if (turnoDTO.getFecha()!=null){
            turno.setFecha(turnoDTO.getFecha());
        }
        return turno;
    }

    @Override
    public String eliminar(Integer id) throws Exception {
        String respuesta = null;
        repository.deleteById(id);
        if (repository.findById(id) != null)
            respuesta = "Eliminado con éxito el turno número: " + id;
        return respuesta;
    }
}
