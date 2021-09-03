package com.ceiba.ticket;

import com.ceiba.BasePrueba;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.movie_projector.servicio.testdatabuilder.MovieProjectorTestDataBuilder;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.excepcion.ExcepcionExistenciaTicket;
import com.ceiba.ticket.excepcion.ExcepcionTiempoDeCambio;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import com.ceiba.ticket.servicio.ServicioActualizarTicket;
import com.ceiba.ticket.servicio.ServicioEliminarTicket;
import com.ceiba.ticket.testdatabuilder.TicketTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ServicioEliminarTicketTest {

    @Test
    public void validateTimeLimitTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioEliminarTicket servicioEliminarTicket = new ServicioEliminarTicket(repositorioTicket,movieProjectorRepositorio,repositorioSeat);
        LocalDate date = LocalDate.now();
        LocalTime sixThirty = LocalTime.now();

        BasePrueba.assertThrows(()->servicioEliminarTicket.validateTimeLimit(date,sixThirty), ExcepcionTiempoDeCambio.class,"El tiempo de cambio paso");
    }

    @Test
    public void validateExistTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioEliminarTicket servicioEliminarTicket = new ServicioEliminarTicket(repositorioTicket,movieProjectorRepositorio,repositorioSeat);
        Mockito.when(repositorioTicket.validarExiste(Mockito.anyLong())).thenReturn(false);
        BasePrueba.assertThrows(()->servicioEliminarTicket.validateExist(1L),ExcepcionExistenciaTicket.class,"No existe el ticket");

    }
    @Test
    public void validateExistSuccesfulTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioEliminarTicket servicioEliminarTicket = new ServicioEliminarTicket(repositorioTicket,movieProjectorRepositorio,repositorioSeat);
        Mockito.when(repositorioTicket.validarExiste(Mockito.anyLong())).thenReturn(true);
        servicioEliminarTicket.validateExist(1L);
        Mockito.verify(repositorioTicket,Mockito.atLeastOnce()).validarExiste(1L);
    }

    @Test
    public void obtenerTicketTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        Mockito.when(repositorioTicket.obtenerTicket(1L)).thenReturn(new TicketTestDataBuilder().build());
        Mockito.when(repositorioTicket.validarExiste(Mockito.anyLong())).thenReturn(true);
        ServicioEliminarTicket servicioEliminarTicket = new ServicioEliminarTicket(repositorioTicket,movieProjectorRepositorio,repositorioSeat);
        servicioEliminarTicket.obtenerTicket(1L);
        Mockito.verify(repositorioTicket).obtenerTicket(1L);

    }
    @Test
    public void validateTimeLimitSuccesfulTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioEliminarTicket servicioEliminarTicket = new ServicioEliminarTicket(repositorioTicket,movieProjectorRepositorio,repositorioSeat);
        ServicioEliminarTicket servicioEliminarTicketSpy = Mockito.spy(servicioEliminarTicket);
        LocalDate date = LocalDate.now().plusDays(2);
        LocalTime sixThirty = LocalTime.now();
        servicioEliminarTicketSpy.validateTimeLimit(date,sixThirty);
        Mockito.verify(servicioEliminarTicketSpy).validateTimeLimit(date,sixThirty);
    }


    @Test
    public void eliminarTicketTest(){
       // Mockito.verify(repositorioSeat,Mockito.atLeastOnce()).validarSeat(1);
        LocalDate date = LocalDate.now().plusDays(2);
        LocalTime sixThirty = LocalTime.now();

        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        Mockito.when(movieProjectorRepositorio.findbyMovieProjectorForId(Mockito.any())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(date).conHourMovie(sixThirty).build());
        Mockito.when(repositorioTicket.validarExiste(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioTicket.obtenerTicket(1L)).thenReturn(new TicketTestDataBuilder().build());
        ServicioEliminarTicket servicioEliminarTicket = new ServicioEliminarTicket(repositorioTicket,movieProjectorRepositorio,repositorioSeat);
        servicioEliminarTicket.eliminarTicket(1L);
        Mockito.verify(repositorioTicket).eliminarTicket(1L);
    }


}
