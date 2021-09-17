package com.ceiba.proyeccion_cine.puerto.dao;

import com.ceiba.proyeccion_cine.modelo.dto.DtoProyeccionCine;

import java.util.List;

public interface DaoProyeccionCine {

    List<DtoProyeccionCine> listarProyeccionCine();
    List<DtoProyeccionCine> listarProyeccionCinePorIdPelicula(Long id);
    DtoProyeccionCine listarProyeccionCinePorId(Long id);
}
