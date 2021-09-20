package com.example.clinicaOdontologicaConORM.services;

import java.util.List;

public interface ServiceInterface<E> {
    E insertar(E entidad);
    List<E> obtenerTodos();
    String actualizar(E entidad);
    String eliminar(Integer id);
    E buscarPorId(Integer id);

}
