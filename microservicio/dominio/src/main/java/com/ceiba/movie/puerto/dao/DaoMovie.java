package com.ceiba.movie.puerto.dao;

import com.ceiba.movie.modelo.dto.DtoMovie;

import java.util.List;

public interface DaoMovie {


    /**
     * Permite listar peliculas
     * @return los Movie
     */

    List<DtoMovie> ListMovie();

}
