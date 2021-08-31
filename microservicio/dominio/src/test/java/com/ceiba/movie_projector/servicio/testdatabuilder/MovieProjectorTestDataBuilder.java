package com.ceiba.movie_projector.servicio.testdatabuilder;

import com.ceiba.movie_projector.modelo.entidad.MovieProjector;

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
        movieProjection = LocalDate.of(2021,8,31);
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

    public MovieProjector build(){
        return new MovieProjector(idMovieProjector,movieProjection,hourMovie,idMovie,idCinema);
    }
}
