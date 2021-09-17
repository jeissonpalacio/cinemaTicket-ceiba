package com.ceiba.proyeccion_cine.modelo.entidad;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ProyeccionCine {

    Integer idMovieProjector;
    LocalDate movieProjection;
    LocalTime hourMovie;
    Integer idMovie;
    Integer idCinema;

    public ProyeccionCine(Integer idMovieProjector, LocalDate movieProjection, LocalTime hourMovie, Integer idMovie, Integer idCinema) {
        this.idMovieProjector = idMovieProjector;
        this.movieProjection = movieProjection;
        this.hourMovie = hourMovie;
        this.idMovie = idMovie;
        this.idCinema = idCinema;
    }
}
