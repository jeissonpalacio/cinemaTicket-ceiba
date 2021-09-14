package com.ceiba.ticket.servicio;

import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepository;
import com.ceiba.seats.excepcion.ExcepcionAvailability;
import com.ceiba.seats.excepcion.ExcepcionExistence;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.excepcion.ExcepcionExistenceTicket;
import com.ceiba.ticket.excepcion.ExcepcionTimeForChange;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ServiceUpdateTicket {

    private final RepositorioTicket repositorioTicket;
    private final RepositorioSeat repositorioSeat;
    private final MovieProjectorRepository movieProjectorRepository;
    private static final String EL_TIEMPO_DE_CAMBIO_PASO= "El tiempo de cambio paso";
    private static final String SOLO_PUEDE_TENER_DOS_ASIENTOS = "Solo puede tener dos asientos";
    private static final String NO_EXISTE_SEAT = "No existe el seat";
    private static final String LA_SILLA_ESTA_RESERVADA = "La silla esta siendo reservada";
    private final String NO_EXISTE_EL_TICKET = "No existe el ticket";


    public ServiceUpdateTicket(RepositorioTicket repositorioTicket, RepositorioSeat repositorioSeat,
                               MovieProjectorRepository movieProjectorRepository){
        this.repositorioTicket = repositorioTicket;
        this.repositorioSeat = repositorioSeat;
        this.movieProjectorRepository = movieProjectorRepository;

    }
    public void validateTimeLimit(LocalDate date,LocalTime time){
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atTime(LocalTime.now());
        LocalDateTime localDateProjection = date.atTime(time);
        final long days = ChronoUnit.DAYS.between(localDateTime, localDateProjection);
        if(days<1){
            throw  new ExcepcionTimeForChange(EL_TIEMPO_DE_CAMBIO_PASO);
        }
    }
    public void validarDisponibilidad(Integer idSeat){
        Long disponibilidad = repositorioSeat.consultavailable(idSeat);
        if(disponibilidad==0){
            throw new ExcepcionAvailability(LA_SILLA_ESTA_RESERVADA);
        }
    }
    public void validateExist(Long idTicket){

        boolean exist = this.repositorioTicket.validateExiste(idTicket);
        if(!exist){
            throw new ExcepcionExistenceTicket(NO_EXISTE_EL_TICKET);
        }
    }
    public void validarExistenciaSeat(Integer idSeat){
        boolean existe = repositorioSeat.validateSeat(idSeat);
        if(!existe){
            throw new ExcepcionExistence(NO_EXISTE_SEAT);
        }
    }
    // Se repite logica que hacer?
    public void actualizarTicket(Ticket ticket){
        validateExist(ticket.getIdTicket());
        ticket.getIdSeats().forEach(value->{
            validarExistenciaSeat(value);
            validarDisponibilidad(value);
        });
        MovieProjector movieProjector = this.movieProjectorRepository.findbyMovieProjectorForId(ticket.getIdMovieProjector());
        validateTimeLimit(movieProjector.getMovieProjection(),movieProjector.getHourMovie());

        this.repositorioSeat.upgradeSeatAvailable(ticket.getIdTicket(),null,1);
        ticket.getIdSeats().forEach(value ->repositorioSeat.upgradeSeat(value,ticket.getIdTicket(),0));
        repositorioTicket.changeProjectionTicket(ticket);
    }

}
