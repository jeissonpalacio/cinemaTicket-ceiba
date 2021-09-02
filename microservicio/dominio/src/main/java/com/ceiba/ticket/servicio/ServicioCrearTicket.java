package com.ceiba.ticket.servicio;

import com.ceiba.movie_projector.exception.ExcepcionTiempoProyeccion;
import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.seats.excepcion.ExcepcionCantidad;
import com.ceiba.seats.excepcion.ExcepcionDisponibilidad;
import com.ceiba.seats.excepcion.ExcepcionExistencia;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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

        LocalTime timeLocal = LocalTime.now();
        Duration duration = Duration.between(timeLocal,time);
        final long days = ChronoUnit.DAYS.between(LocalDate.now(), date);
        if(days<0){
            throw new ExcepcionTiempoProyeccion(ERROR_DE_TIEMPO);
        }else if(days==0){
            if(duration.toMinutes()<=60){
                throw new ExcepcionTiempoProyeccion(NO_SE_PUEDE_HACER_UNA_HORA_ANTES);
            }
        }
    }

    public Double calculateHalfPrice(LocalDate date, double price){
        if(date.getDayOfWeek() == DayOfWeek.TUESDAY || DayOfWeek.THURSDAY == date.getDayOfWeek()){
            price = price/2;
        }

        return price;
    }


    public Long crearServicioTicket(Ticket ticket){
        ticket.getIdSeats().forEach(e->{
            validarExistenciaSeat(e);
            validarDisponibilidad(e);
        });
        // puede ir aqui sin tener que crear un metodo de que retorne un movieProjector
        MovieProjector movieProjector = this.movieProjectorRepositorio.findbyMovieProjectorForId(ticket.getIdMovieProjector());


        // Este metodo debe de ser llamado desde ticket porque no contiene acceso a datos o se queda en el servicio
        // Al igual que los metodos calculateHalfPrice
        purchaseEnabled(movieProjector.getMovieProjection(),movieProjector.getHourMovie());
        ticket.setAmount(calculateHalfPrice(movieProjector.getMovieProjection(),ticket.getAmount()));
        Long id = this.repositorioTicket.crearTicket(ticket);
        ticket.getIdSeats().forEach(value ->{
            repositorioSeat.actualizarSeat(value,id,0);
        });
        return id;
    }
}
