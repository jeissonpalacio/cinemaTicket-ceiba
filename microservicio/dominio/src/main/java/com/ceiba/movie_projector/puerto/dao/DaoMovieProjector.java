package com.ceiba.movie_projector.puerto.dao;

import com.ceiba.movie_projector.modelo.dto.DtoMovieProjector;

import java.util.List;

public interface DaoMovieProjector {

    List<DtoMovieProjector> listMovieProjector();
    List<DtoMovieProjector> listMovieProjectorByIdMovie(Long id);
    DtoMovieProjector listMovieProjectoById(Long id);
}
