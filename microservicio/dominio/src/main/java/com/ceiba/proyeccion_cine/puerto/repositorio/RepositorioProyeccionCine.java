package com.ceiba.proyeccion_cine.puerto.repositorio;

import com.ceiba.proyeccion_cine.modelo.entidad.ProyeccionCine;

public interface RepositorioProyeccionCine {

    ProyeccionCine buscarProyeccionCinePorId(Integer idMovieProjector);
}
