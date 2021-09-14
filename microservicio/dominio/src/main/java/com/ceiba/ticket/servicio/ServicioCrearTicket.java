package com.ceiba.ticket.servicio;

import com.ceiba.movie_projector.exception.ExcepcionTiempoProyeccion;
import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.seats.excepcion.ExcepcionDisponibilidad;
import com.ceiba.seats.excepcion.ExcepcionExistencia;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class ServicioCrearTicket {

    private static final String NO_EXISTE_SEAT = "No existe el seat";
    private static final String LA_SILLA_ESTA_RESERVADA = "La silla esta siendo reservada";
    private static final String ERROR_DE_TIEMPO = "error de tiempo, la funcion ya paso";
    private static final String NO_SE_PUEDE_HACER_UNA_HORA_ANTES = "error reserva antes de una hora";

    private final RepositorioTicket repositorioTicket;
    private final RepositorioSeat  repositorioSeat;
    private final MovieProjectorRepositorio movieProjectorRepositorio;

    public ServicioCrearTicket(RepositorioTicket repositorioTicket, RepositorioSeat repositorioSeat,
                               MovieProjectorRepositorio movieProjectorRepositorio){
        this.repositorioTicket = repositorioTicket;
        this.repositorioSeat = repositorioSeat;
        this.movieProjectorRepositorio = movieProjectorRepositorio;
    }


    public void validarExistenciaSeat(Integer idSeat){
        boolean existe = repositorioSeat.validarSeat(idSeat);
        if(!existe){
            throw new ExcepcionExistencia(NO_EXISTE_SEAT);
        }
    }
    public void validarDisponibilidad(Integer idSeat){
        Long disponibilidad = repositorioSeat.consultavailable(idSeat);
        if(disponibilidad==0){
            throw new ExcepcionDisponibilidad(LA_SILLA_ESTA_RESERVADA);
        }
    }
    public void purchaseEnabled(LocalDate date, LocalTime time){
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atTime(LocalTime.now());
        LocalDateTime localDateProjection = date.atTime(time);
        long minutes = ChronoUnit.MINUTES.between(localDateTime, localDateProjection);
        final long days = ChronoUnit.DAYS.between(localDate,localDateProjection);
        if(localDateTime.isAfter(localDateProjection)){
            throw new ExcepcionTiempoProyeccion(ERROR_DE_TIEMPO);

        }else if(days==0 && minutes<=60){
                throw new ExcepcionTiempoProyeccion(NO_SE_PUEDE_HACER_UNA_HORA_ANTES);
        }
    }

    public Double calculateHalfPrice(LocalDate date, double price){
        if(date.getDayOfWeek() == DayOfWeek.TUESDAY || DayOfWeek.THURSDAY == date.getDayOfWeek()){
            price = price/3;
        }

        return price;
    }


    public Long crearServicioTicket(Ticket ticket){
        ticket.getIdSeats().forEach(e->{
            validarExistenciaSeat(e);
            validarDisponibilidad(e);
        });
        MovieProjector movieProjector = this.movieProjectorRepositorio.findbyMovieProjectorForId(ticket.getIdMovieProjector());
        purchaseEnabled(movieProjector.getMovieProjection(),movieProjector.getHourMovie());
        ticket.setAmount(calculateHalfPrice(movieProjector.getMovieProjection(),ticket.getAmount()));
        Long id = this.repositorioTicket.crearTicket(ticket);
        ticket.getIdSeats().forEach(value -> repositorioSeat.actualizarSeat(value,id,0));
        return id;
    }
}
