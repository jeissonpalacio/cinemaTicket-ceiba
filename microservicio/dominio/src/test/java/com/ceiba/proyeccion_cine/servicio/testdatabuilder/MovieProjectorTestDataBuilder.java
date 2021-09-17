package com.ceiba.proyeccion_cine.servicio.testdatabuilder;

import com.ceiba.proyeccion_cine.modelo.entidad.ProyeccionCine;

import java.time.LocalDate;
import java.time.LocalTime;

public class MovieProjectorTestDataBuilder {

    Integer idMovieProjector;
    LocalDate movieProjection;
    LocalTime hourMovie;
    Integer idMovie;
    Integer idCinema;

    public MovieProjectorTestDataBuilder(){
        idMovieProjector = 1;
        movieProjection = LocalDate.now().plusDays(2);
        hourMovie = LocalTime.of(13,30,00) ;
        idMovie = 1;
        idCinema = 1;
    }

    public MovieProjectorTestDataBuilder conIdMovieProjector(Integer idMovieProjector){
        this.idMovieProjector = idMovieProjector;
        return this;
    }
    public MovieProjectorTestDataBuilder conMovieProjection(LocalDate movieProjection){
        this.movieProjection = movieProjection;
        return this;
    }

    public MovieProjectorTestDataBuilder conHourMovie(LocalTime hourMovie){
        this.hourMovie = hourMovie;
        return this;
    }
    public MovieProjectorTestDataBuilder conIdMovie(Integer idMovie){
        this.idMovie = idMovie;
        return this;
    }
    public MovieProjectorTestDataBuilder conIdCinema(Integer idCinema){
        this.idCinema = idCinema;
        return this;
    }

    public ProyeccionCine build(){
        return new ProyeccionCine(idMovieProjector,movieProjection,hourMovie,idMovie,idCinema);
    }
}
