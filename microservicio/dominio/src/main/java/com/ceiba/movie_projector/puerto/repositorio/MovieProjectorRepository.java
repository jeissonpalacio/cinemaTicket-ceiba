package com.ceiba.movie_projector.puerto.repositorio;

import com.ceiba.movie_projector.modelo.entidad.MovieProjector;

public interface MovieProjectorRepository {

    MovieProjector findbyMovieProjectorForId(Integer idMovieProjector);
}
