package com.ceiba.ticket.servicio;

import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.excepcion.ExcepcionExistenciaTicket;
import com.ceiba.ticket.excepcion.ExcepcionTiempoDeCambio;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

import java.time.LocalDate;
import java.time.Period;

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
    // Esta logica se coloca en otro servicio ServicioValidaciones o se conserva porque puede ser logicas diferentes
    public void validateTimeLimit(LocalDate time){
        Period period = Period.between(LocalDate.now(),time);
        if(period.getDays()<1){
            throw  new ExcepcionTiempoDeCambio(EL_TIEMPO_DE_CAMBIO_PASO);
        }
    }

    public void eliminarTicket(Long id){
        // quitar de seats el id, porque es una relacion de ticket - seats
        Ticket ticket = obtenerTicket(id);
        MovieProjector movieProjector = this.movieProjectorRepositorio.findbyMovieProjectorForId(ticket.getIdMovieProjector());
        validateTimeLimit(movieProjector.getMovieProjection());
        // con este id hacer el update en seat
        this.repositorioSeat.actualizarSeatAvailable(id,null,1);
        this.repositorioTicket.eliminarTicket(id);
    }

    public Ticket obtenerTicket(Long id){
        validateExist(id);
        return repositorioTicket.obtenerTicket(id);
    }

}
