package com.ceiba.pelicula.puerto.dao;

import com.ceiba.pelicula.modelo.dto.DtoPelicula;

import java.util.List;

public interface DaoPelicula {


    /**
     * Permite listar peliculas
     * @return los Movie
     */

    List<DtoPelicula> listarPelicula();

}
