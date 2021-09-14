package com.ceiba.ticket.servicio;

import com.ceiba.movie_projector.exception.ExcepcionProjectionTime;
import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepository;
import com.ceiba.seats.excepcion.ExcepcionAvailability;
import com.ceiba.seats.excepcion.ExcepcionExistence;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class ServiceCreateTicket {

    private static final String NO_EXISTE_SEAT = "No existe el seat";
    private static final String LA_SILLA_ESTA_RESERVADA = "La silla esta siendo reservada";
    private static final String ERROR_DE_TIEMPO = "error de tiempo, la funcion ya paso";
    private static final String NO_SE_PUEDE_HACER_UNA_HORA_ANTES = "error reserva antes de una hora";

    private final RepositorioTicket repositorioTicket;
    private final RepositorioSeat  repositorioSeat;
    private final MovieProjectorRepository movieProjectorRepository;
    private final ServiceCalculateHalfPrice serviceCalculateHalfPrice;
    public ServiceCreateTicket(RepositorioTicket repositorioTicket, RepositorioSeat repositorioSeat,
                               MovieProjectorRepository movieProjectorRepository,ServiceCalculateHalfPrice serviceCalculateHalfPrice){
        this.repositorioTicket = repositorioTicket;
        this.repositorioSeat = repositorioSeat;
        this.movieProjectorRepository = movieProjectorRepository;
        this.serviceCalculateHalfPrice = serviceCalculateHalfPrice;
    }


    private void validateExistenceSeat(Integer idSeat){
        boolean existe = repositorioSeat.validateSeat(idSeat);
        if(!existe){
            throw new ExcepcionExistence(NO_EXISTE_SEAT);
        }
    }
    private void validateAvailability(Integer idSeat){
        Long disponibilidad = repositorioSeat.consultavailable(idSeat);
        if(disponibilidad==0){
            throw new ExcepcionAvailability(LA_SILLA_ESTA_RESERVADA);
        }
    }
    private void purchaseEnabled(LocalDate date, LocalTime time){
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atTime(LocalTime.now());
        LocalDateTime localDateProjection = date.atTime(time);
        long minutes = ChronoUnit.MINUTES.between(localDateTime, localDateProjection);
        final long days = ChronoUnit.DAYS.between(localDate,localDateProjection);
        if(localDateTime.isAfter(localDateProjection)){
            throw new ExcepcionProjectionTime(ERROR_DE_TIEMPO);

        }else if(days==0 && minutes<=60){
                throw new ExcepcionProjectionTime(NO_SE_PUEDE_HACER_UNA_HORA_ANTES);
        }
    }

    public Long ejecutar(Ticket ticket){
        ticket.getIdSeats().forEach(e->{
            validateExistenceSeat(e);
            validateAvailability(e);
        });
        MovieProjector movieProjector = this.movieProjectorRepository.findbyMovieProjectorForId(ticket.getIdMovieProjector());
        purchaseEnabled(movieProjector.getMovieProjection(),movieProjector.getHourMovie());
        ticket.setAmount(serviceCalculateHalfPrice.ejecutar(movieProjector.getMovieProjection(),ticket.getAmount()));
        Long id = this.repositorioTicket.createTicket(ticket);
        ticket.getIdSeats().forEach(value -> repositorioSeat.upgradeSeat(value,id,0));
        return id;
    }
}
