package com.ceiba.ticket.servicio;

import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepository;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.excepcion.ExcepcionExistenceTicket;
import com.ceiba.ticket.excepcion.ExcepcionTimeForChange;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ServiceDeleteTicket {
    private final RepositorioTicket repositorioTicket;
    private final MovieProjectorRepository movieProjectorRepository;
    private final RepositorioSeat repositorioSeat;

    private final String NO_EXISTE_EL_TICKET = "No existe el ticket";
    private final String EL_TIEMPO_DE_CAMBIO_PASO= "El tiempo de cambio paso";
    public ServiceDeleteTicket(RepositorioTicket repositorioTicket,
                               MovieProjectorRepository movieProjectorRepository,
                               RepositorioSeat repositorioSeat
                                  ){
        this.repositorioTicket = repositorioTicket;
        this.movieProjectorRepository = movieProjectorRepository;
        this.repositorioSeat = repositorioSeat;
    }

    public void validateExist(Long idTicket){

        boolean exist = this.repositorioTicket.validateExiste(idTicket);
        if(!exist){
            throw new ExcepcionExistenceTicket(NO_EXISTE_EL_TICKET);
        }
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

    public void eliminarTicket(Long id){
        Ticket ticket = obtenerTicket(id);
        MovieProjector movieProjector = this.movieProjectorRepository.findbyMovieProjectorForId(ticket.getIdMovieProjector());
        validateTimeLimit(movieProjector.getMovieProjection(),movieProjector.getHourMovie());
        this.repositorioSeat.upgradeSeatAvailable(id,null,1);
        this.repositorioTicket.deleteTicket(id);
    }

    public Ticket obtenerTicket(Long id){
        validateExist(id);
        return repositorioTicket.getTicket(id);
    }

}
