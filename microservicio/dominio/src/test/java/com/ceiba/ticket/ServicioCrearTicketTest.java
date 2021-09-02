package com.ceiba.ticket;

import com.ceiba.BasePrueba;
import com.ceiba.movie_projector.exception.ExcepcionTiempoProyeccion;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.seats.excepcion.ExcepcionCantidad;
import com.ceiba.seats.excepcion.ExcepcionDisponibilidad;
import com.ceiba.seats.excepcion.ExcepcionExistencia;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import com.ceiba.ticket.servicio.ServicioCrearTicket;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ServicioCrearTicketTest {

    @Test
    public void validarExistenciaSeatTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        Mockito.when(repositorioSeat.validarSeat(Mockito.anyInt())).thenReturn(false);
        ServicioCrearTicket servicioCrearTicket = new ServicioCrearTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearTicket.validarExistenciaSeat(1), ExcepcionExistencia.class,"No existe el seat");
    }


    @Test
    public void validarDisponibilidadTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);

        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(0L);
        ServicioCrearTicket servicioCrearTicket = new ServicioCrearTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);
        BasePrueba.assertThrows(()->servicioCrearTicket.validarDisponibilidad(1), ExcepcionDisponibilidad.class,"La silla esta siendo reservada");


    }

    @Test
    public void purchaseEnabledDatePastTest(){
        LocalDate date = LocalDate.now().minusDays(1);
        LocalTime sixThirty = LocalTime.now().minus(Duration.ofHours(1));
        System.out.println("date"+date+"tiempo" + sixThirty);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioCrearTicket servicioCrearTicket = new ServicioCrearTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);
        BasePrueba.assertThrows(()->servicioCrearTicket.purchaseEnabled(date,sixThirty), ExcepcionTiempoProyeccion.class,"error de tiempo, la funcion ya paso");


    }

    @Test
    public void purchaseEnabledHoursTest(){
        LocalDate date = LocalDate.now();
        LocalTime sixThirty = LocalTime.now().minus(Duration.ofMinutes(50));
        //System.out.println("date "+date+" tiempo " + sixThirty);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioCrearTicket servicioCrearTicket = new ServicioCrearTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);
        BasePrueba.assertThrows(()->servicioCrearTicket.purchaseEnabled(date,sixThirty), ExcepcionTiempoProyeccion.class,"error reserva antes de una hora");


    }

    @Test
    public void validatePriceTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioCrearTicket servicioCrearTicket = new ServicioCrearTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);
        BasePrueba.assertThrows(()->servicioCrearTicket.validatePrice(0), ExcepcionCantidad.class,"error de cantidad");
    }

    @Test
    public void calculateHalfPriceTest(){
        LocalDate date = LocalDate.of(2021, 9, 2);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioCrearTicket servicioCrearTicket = new ServicioCrearTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);
        Double result = 7500.00;
        Assert.assertEquals(servicioCrearTicket.calculateHalfPrice(date,15000.00),result);
    }

}
