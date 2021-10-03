package com.example.clinicaOdontologicaConORM.service.impl;

import com.example.clinicaOdontologicaConORM.config.SpringConfig;
import com.example.clinicaOdontologicaConORM.dto.OdontologoDTO;
import com.example.clinicaOdontologicaConORM.dto.PacienteDTO;
import com.example.clinicaOdontologicaConORM.dto.TurnoDTO;
import com.example.clinicaOdontologicaConORM.exceptions.BadRequestException;
import com.example.clinicaOdontologicaConORM.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaConORM.persistence.entities.Turno;
import com.example.clinicaOdontologicaConORM.persistence.repository.TurnoRepository;
import com.example.clinicaOdontologicaConORM.service.interfaces.TurnoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public TurnoDTO insertar(TurnoDTO entidad) throws BadRequestException, ResourceNotFoundException {
        TurnoDTO respuesta;
        if (entidad.getPaciente().getId() != null && entidad.getOdontologo() != null){
            PacienteDTO pacienteBuscado = pacienteService.buscarPorId(entidad.getPaciente().getId());
            OdontologoDTO odontologoBuscado = odontologoService.buscarPorId(entidad.getOdontologo().getId());
            if (verificarDisponibilidadTurno(odontologoBuscado.getId(), entidad.getFecha())){
                //Creamos en BDD
                Turno turno = mapper.getModelMapper().map(entidad, Turno.class);
                respuesta = mapper.getModelMapper().map(repository.save(turno), TurnoDTO.class);

                //Setemos el odntologo y paciente al JSON de la respuesta
                respuesta.setPaciente(pacienteBuscado);
                respuesta.setOdontologo(odontologoBuscado);
            } else {
                throw new ResourceNotFoundException("El odontologo con id " + odontologoBuscado.getId() + " ya tiene un turno agendado en la fecha "+ entidad.getFecha());
            }
        } else {
            throw new BadRequestException("Faltan introducir el id del paciente u odontologo para crear el turno");
        }
        return respuesta;
    }

    private Boolean verificarDisponibilidadTurno(Integer idOdontologo, LocalDateTime fechaTurno) throws ResourceNotFoundException {
        Boolean respuesta = true;
        List<Turno> listaTurnos = repository.findAll();
        for (Turno t: listaTurnos){
            TurnoDTO turnoDTO = mapper.getModelMapper().map(t,TurnoDTO.class);
            if (turnoDTO.getOdontologo().getId().equals(idOdontologo) && turnoDTO.getFecha().equals(fechaTurno)){
                respuesta = false;
            }
        }
        return respuesta;
    }


    @Override
    public List<TurnoDTO> obtenerTodos() throws ResourceNotFoundException{
        List<TurnoDTO> turnos = mapper.getModelMapper().map(repository.findAll(), List.class);
        if (turnos.size()<=0){
            throw new ResourceNotFoundException ("No hay turnos cargados");
        }
        return turnos;
    }

    @Override
    public String actualizar(TurnoDTO entidad) throws ResourceNotFoundException, BadRequestException {
        String respuesta;
        if (entidad.getId()!=null){
            Optional<Turno> turnoAModificar = repository.findById(entidad.getId());
            if (turnoAModificar.isPresent()){
                repository.save(this.actualizarEnBDD(turnoAModificar.get(), entidad));
                respuesta = "Actualización con éxito del turno número: " + entidad.getId();
            }
            else {
                throw new ResourceNotFoundException("No fue posible encontrar el turno en la base de datos");
            }
        } else {
            throw new BadRequestException("No se introdujo el id del turno a modificar");
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
    public String eliminar(Integer id) throws ResourceNotFoundException {
        String respuesta;
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            respuesta = "Eliminado con éxito el turno número: " + id;
        }
        else
            throw new ResourceNotFoundException("No fue encontrado el turno en la base de datos");

        return respuesta;
    }

    public List<TurnoDTO> turnosProxSemana() throws ResourceNotFoundException {
        LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime proximaSemana = hoy.plusDays(7);
        List<TurnoDTO> listaTodosTurnos = this.obtenerTodos();
        List<TurnoDTO> turnosProximaSemana = new ArrayList<>();
        for (TurnoDTO turno:listaTodosTurnos){
            if ((turno.getFecha().isBefore(proximaSemana) && turno.getFecha().isAfter(hoy)) || turno.getFecha().isEqual(hoy) || turno.getFecha().isEqual(proximaSemana)){
                turnosProximaSemana.add(turno);
            }
        }
        if (turnosProximaSemana.size()<=0)
            throw new ResourceNotFoundException("No hay turnos agendados para la próxima semana");
        return turnosProximaSemana;
    }
}
