package com.example.clinicaOdontologicaConORM.service.impl;

import com.example.clinicaOdontologicaConORM.config.SpringConfig;
import com.example.clinicaOdontologicaConORM.dto.OdontologoDTO;
import com.example.clinicaOdontologicaConORM.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaConORM.persistence.entities.Odontologo;
import com.example.clinicaOdontologicaConORM.persistence.repository.OdontologoRepository;
import com.example.clinicaOdontologicaConORM.service.interfaces.OdontologoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService implements OdontologoServiceInterface {

    @Autowired
    OdontologoRepository repository;

    @Autowired
    SpringConfig mapper;


    @Override
    public OdontologoDTO insertar(OdontologoDTO entidad){
        Odontologo odontologo = mapper.getModelMapper().map(entidad, Odontologo.class);
        return mapper.getModelMapper().map(repository.save(odontologo), OdontologoDTO.class);

    }

    @Override
    public List<OdontologoDTO> obtenerTodos() throws ResourceNotFoundException{
        List<Odontologo> lista = repository.findAll();
        if (lista.size() == 0){
            throw new ResourceNotFoundException("No hay odontólogos aún cargados en la base de datos");
        }
        return mapper.getModelMapper().map(lista, List.class);
    }

    @Override
    public String actualizar(OdontologoDTO entidad) throws ResourceNotFoundException {
        String respuesta;
        Optional<Odontologo> odontologoAModificar = repository.findById(entidad.getId());
        if(odontologoAModificar.isPresent()){
            repository.save(this.actualizarEnBDD(odontologoAModificar.get(), entidad));
            respuesta = "Actualización con éxito del odontólogo con id " + entidad.getId();
        }else {
            throw new ResourceNotFoundException("No se logró actualizar el odontólogo. El odontólogo con id " + entidad.getId() + " no fue encontrado en la base de datos");
        }
        return respuesta;
    }

    private Odontologo actualizarEnBDD(Odontologo odontologo, OdontologoDTO odontologoDto) {
        if (odontologoDto.getNombre() != null) {
            odontologo.setNombre(odontologoDto.getNombre());
        }
        if (odontologoDto.getApellido() != null) {
            odontologo.setApellido(odontologoDto.getApellido());
        }
        if (odontologoDto.getMatricula() != null) {
            odontologo.setMatricula(odontologoDto.getMatricula());
        }
        return odontologo;
    }

    @Override
    public String eliminar(Integer id) throws ResourceNotFoundException{
        String respuesta;
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            respuesta = "Eliminado con éxito";
        } else {
            throw new ResourceNotFoundException("No se logró eliminar el odontologo de la base de datos. El id " + id +" no fue encontrado.");
        }
        return respuesta;
    }

    public OdontologoDTO buscarPorId(Integer id) throws ResourceNotFoundException {
        Odontologo odontologoRespuesta = repository.findById(id).orElse(null);
        if (odontologoRespuesta != null){
            return mapper.getModelMapper().map(odontologoRespuesta, OdontologoDTO.class);
        } else {
            throw new ResourceNotFoundException ("No fue encontrado el odontologo con id " + id);
        }
    }

    @Override
    public Set<OdontologoDTO> obtenerOdontologosPorSuApellidoLike(String apellidoOdontolog) {
        Set<Odontologo> odontologos = repository.getOdontologoByApellidoLike(apellidoOdontolog);
        return mapper.getModelMapper().map(odontologos, Set.class);
    }
}
