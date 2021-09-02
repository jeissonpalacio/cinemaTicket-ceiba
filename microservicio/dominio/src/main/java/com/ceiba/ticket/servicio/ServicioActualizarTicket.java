package com.ceiba.ticket.servicio;

import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.seats.excepcion.ExcepcionCantidad;
import com.ceiba.seats.excepcion.ExcepcionDisponibilidad;
import com.ceiba.seats.excepcion.ExcepcionExistencia;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.excepcion.ExcepcionTiempoDeCambio;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ServicioActualizarTicket {

    private final RepositorioTicket repositorioTicket;
    private final RepositorioSeat repositorioSeat;
    private final MovieProjectorRepositorio movieProjectorRepositorio;
    private static final String EL_TIEMPO_DE_CAMBIO_PASO= "El tiempo de cambio paso";
    private static final String SOLO_PUEDE_TENER_DOS_ASIENTOS = "Solo puede tener dos asientos";
    private static final String NO_EXISTE_SEAT = "No existe el seat";
    private static final String LA_SILLA_ESTA_RESERVADA = "La silla esta siendo reservada";


    public ServicioActualizarTicket(RepositorioTicket repositorioTicket, RepositorioSeat repositorioSeat,
                                    MovieProjectorRepositorio movieProjectorRepositorio){
        this.repositorioTicket = repositorioTicket;
        this.repositorioSeat = repositorioSeat;
        this.movieProjectorRepositorio = movieProjectorRepositorio;

    }
    public void validateTimeLimit(LocalDate time){
        final long days = ChronoUnit.DAYS.between(LocalDate.now(), time);
        if(days<1){
            throw  new ExcepcionTiempoDeCambio(EL_TIEMPO_DE_CAMBIO_PASO);
        }
    }
    public void validarCantidad(List<Integer> listId){
        boolean existe = listId.size()>0 && listId.size()<=2;
        if(!existe){
            throw new ExcepcionCantidad(SOLO_PUEDE_TENER_DOS_ASIENTOS);
        }
    };
    public void validarDisponibilidad(Integer idSeat){
        Long disponibilidad = repositorioSeat.consultavailable(idSeat);
        if(disponibilidad==0){
            throw new ExcepcionDisponibilidad(LA_SILLA_ESTA_RESERVADA);
        }
    }

    public void validarExistenciaSeat(Integer idSeat){
        boolean existe = repositorioSeat.validarSeat(idSeat);
        if(!existe){
            throw new ExcepcionExistencia(NO_EXISTE_SEAT);
        }
    }
    // Se repite logica que hacer?
    public void actualizarTicket(Ticket ticket){
        validarCantidad(ticket.getIdSeats());
        ticket.getIdSeats().forEach(value->{
            validarExistenciaSeat(value);
            validarDisponibilidad(value);
        });
        MovieProjector movieProjector = this.movieProjectorRepositorio.findbyMovieProjectorForId(ticket.getIdMovieProjector());
        validateTimeLimit(movieProjector.getMovieProjection());

        // CAMBIAR AQUI PARA COLOCAR LAS SILLAS CON null en id saet
        this.repositorioSeat.actualizarSeatAvailable(ticket.getIdTicket(),null,1);
        ticket.getIdSeats().forEach(value ->{
            repositorioSeat.actualizarSeat(value,ticket.getIdTicket(),0);
        });
        repositorioTicket.cambiarProyeccionTicket(ticket);
    }

}
