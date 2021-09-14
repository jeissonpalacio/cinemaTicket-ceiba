package com.ceiba.ticket;

import com.ceiba.BasePrueba;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepository;
import com.ceiba.movie_projector.servicio.testdatabuilder.MovieProjectorTestDataBuilder;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.excepcion.ExcepcionExistenceTicket;
import com.ceiba.ticket.excepcion.ExcepcionTimeForChange;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import com.ceiba.ticket.servicio.ServiceDeleteTicket;
import com.ceiba.ticket.testdatabuilder.TicketTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceDeleteTicketTest {

    @Test
    public void validateTimeLimitTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceDeleteTicket serviceDeleteTicket = new ServiceDeleteTicket(repositorioTicket, movieProjectorRepository,repositorioSeat);
        LocalDate date = LocalDate.now();
        LocalTime sixThirty = LocalTime.now();
        Mockito.when(movieProjectorRepository.findbyMovieProjectorForId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(date).conHourMovie(sixThirty).build());
        Mockito.when(repositorioTicket.validateExiste(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioTicket.getTicket(1L)).thenReturn(new TicketTestDataBuilder().build());
        BasePrueba.assertThrows(()-> serviceDeleteTicket.eliminarTicket(1L), ExcepcionTimeForChange.class,"El tiempo de cambio paso");
    }

    @Test
    public void validateExistTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceDeleteTicket serviceDeleteTicket = new ServiceDeleteTicket(repositorioTicket, movieProjectorRepository,repositorioSeat);
        Mockito.when(repositorioTicket.validateExiste(Mockito.anyLong())).thenReturn(false);
        BasePrueba.assertThrows(()-> serviceDeleteTicket.eliminarTicket(1L), ExcepcionExistenceTicket.class,"No existe el ticket");

    }


    @Test
    public void obtenerTicketTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        Mockito.when(repositorioTicket.getTicket(1L)).thenReturn(new TicketTestDataBuilder().build());
        Mockito.when(repositorioTicket.validateExiste(Mockito.anyLong())).thenReturn(true);
        ServiceDeleteTicket serviceDeleteTicket = new ServiceDeleteTicket(repositorioTicket, movieProjectorRepository,repositorioSeat);
        serviceDeleteTicket.obtenerTicket(1L);
        Mockito.verify(repositorioTicket).getTicket(1L);

    }

    @Test
    public void eliminarTicketTest(){
       // Mockito.verify(repositorioSeat,Mockito.atLeastOnce()).validarSeat(1);
        LocalDate date = LocalDate.now().plusDays(2);
        LocalTime sixThirty = LocalTime.now();

        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        Mockito.when(movieProjectorRepository.findbyMovieProjectorForId(Mockito.any())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(date).conHourMovie(sixThirty).build());
        Mockito.when(repositorioTicket.validateExiste(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioTicket.getTicket(1L)).thenReturn(new TicketTestDataBuilder().build());
        ServiceDeleteTicket serviceDeleteTicket = new ServiceDeleteTicket(repositorioTicket, movieProjectorRepository,repositorioSeat);
        serviceDeleteTicket.eliminarTicket(1L);
        Mockito.verify(repositorioTicket).deleteTicket(1L);
    }


}
