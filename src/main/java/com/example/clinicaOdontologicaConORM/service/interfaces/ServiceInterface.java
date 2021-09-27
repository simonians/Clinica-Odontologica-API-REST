package com.example.clinicaOdontologicaConORM.service.interfaces;

import java.util.List;

public interface ServiceInterface<E> {
    E insertar(E entidad) throws Exception;
    List<E> obtenerTodos() throws Exception;
    String actualizar(E entidad) throws Exception;
    String eliminar(Integer id) throws Exception;
}
