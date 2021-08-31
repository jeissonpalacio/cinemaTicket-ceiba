package com.ceiba.movie_projector.servicio;

import com.ceiba.movie_projector.exception.ExcepcionTiempoProyeccion;
import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.seats.excepcion.ExcepcionCantidad;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;


public class ServicioConsultaMovieProjector {

    MovieProjectorRepositorio movieProjectorRepositorio;

    private static final String ERROR_DE_TIEMPO = "error de tiempo";
    private static final String ERROR_CANTIDAD_INCORRECTA = "error de cantidad";

    public ServicioConsultaMovieProjector(MovieProjectorRepositorio movieProjectorRepositorio) {
        this.movieProjectorRepositorio = movieProjectorRepositorio;
    }

    public Double calculateHalfPrice(LocalDate date, double price){
        validatePrice(price);

        if(date.getDayOfWeek() == DayOfWeek.TUESDAY || DayOfWeek.THURSDAY == date.getDayOfWeek()){
            price = price/2;
        }

        return price;
    }

    public void validatePrice(double price){
        if(price<=0){
            throw new ExcepcionCantidad(ERROR_CANTIDAD_INCORRECTA);
        }
    }

    public void purchaseEnabled(LocalTime time){
        LocalTime timeLocal = LocalTime.now();
        Duration duration = Duration.between(timeLocal,time);
        if(duration.toMinutes()<=60){
            throw new ExcepcionTiempoProyeccion(ERROR_DE_TIEMPO);
        }
    }



    public MovieProjector findMovieProjectorById(Integer idMovieProjector){

        return this.movieProjectorRepositorio.findbyMovieProjectorForId(idMovieProjector);
    }
}
