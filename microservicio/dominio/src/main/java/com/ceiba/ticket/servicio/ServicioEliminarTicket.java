package com.ceiba.ticket.servicio;

import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.excepcion.ExcepcionExistenciaTicket;
import com.ceiba.ticket.excepcion.ExcepcionTiempoDeCambio;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class ServicioEliminarTicket {
    private final RepositorioTicket repositorioTicket;
    private final MovieProjectorRepositorio movieProjectorRepositorio;
    private final RepositorioSeat repositorioSeat;

    private final String NO_EXISTE_EL_TICKET = "No existe el ticket";
    private final String EL_TIEMPO_DE_CAMBIO_PASO= "El tiempo de cambio paso";
    public ServicioEliminarTicket(RepositorioTicket repositorioTicket,
                                  MovieProjectorRepositorio movieProjectorRepositorio,
                                  RepositorioSeat repositorioSeat
                                  ){
        this.repositorioTicket = repositorioTicket;
        this.movieProjectorRepositorio = movieProjectorRepositorio;
        this.repositorioSeat = repositorioSeat;
    }

    public void validateExist(Long idTicket){

        boolean exist = this.repositorioTicket.validarExiste(idTicket);
        if(!exist){
            throw new ExcepcionExistenciaTicket(NO_EXISTE_EL_TICKET);
        }
    }
    public void validateTimeLimit(LocalDate date,LocalTime time){
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atTime(LocalTime.now());
        LocalDateTime localDateProjection = date.atTime(time);
        final long days = ChronoUnit.DAYS.between(localDateTime, localDateProjection);
        if(days<1){
            throw  new ExcepcionTiempoDeCambio(EL_TIEMPO_DE_CAMBIO_PASO);
        }
    }

    public void eliminarTicket(Long id){
        Ticket ticket = obtenerTicket(id);
        MovieProjector movieProjector = this.movieProjectorRepositorio.findbyMovieProjectorForId(ticket.getIdMovieProjector());
        validateTimeLimit(movieProjector.getMovieProjection(),movieProjector.getHourMovie());
        this.repositorioSeat.actualizarSeatAvailable(id,null,1);
        this.repositorioTicket.eliminarTicket(id);
    }

    public Ticket obtenerTicket(Long id){
        validateExist(id);
        return repositorioTicket.obtenerTicket(id);
    }

}
