package com.ceiba.ticket;

import com.ceiba.BasePrueba;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepository;
import com.ceiba.movie_projector.servicio.testdatabuilder.MovieProjectorTestDataBuilder;
import com.ceiba.seats.excepcion.ExcepcionAvailability;
import com.ceiba.seats.excepcion.ExcepcionExistence;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.excepcion.ExcepcionExistenceTicket;
import com.ceiba.ticket.excepcion.ExcepcionTimeForChange;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import com.ceiba.ticket.servicio.ServiceUpdateTicket;
import com.ceiba.ticket.testdatabuilder.TicketTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ServiceUpdateTicketTest {

    @Test
    public void validateTimeLimitTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceUpdateTicket serviceUpdateTicket = new ServiceUpdateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        LocalDate time = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        Ticket ticket = new TicketTestDataBuilder().build();
        Mockito.when(movieProjectorRepository.findbyMovieProjectorForId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(time).conHourMovie(localTime).build());
        Mockito.when(repositorioTicket.validateExiste(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(1L);

        BasePrueba.assertThrows(()-> serviceUpdateTicket.actualizarTicket(ticket), ExcepcionTimeForChange.class,"El tiempo de cambio paso");
    }

    @Test
    public void validarDisponibilidadTest(){
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(0L);
        ServiceUpdateTicket serviceUpdateTicket = new ServiceUpdateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        Ticket ticket = new TicketTestDataBuilder().build();
        Mockito.when(movieProjectorRepository.findbyMovieProjectorForId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().build());
        Mockito.when(repositorioTicket.validateExiste(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(true);
        BasePrueba.assertThrows(()-> serviceUpdateTicket.actualizarTicket(ticket), ExcepcionAvailability.class,"La silla esta siendo reservada");


    }

    @Test
    public void validarExistenciaSeatTest(){
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(false);
        ServiceUpdateTicket serviceUpdateTicket = new ServiceUpdateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        Ticket ticket = new TicketTestDataBuilder().build();
        Mockito.when(repositorioTicket.validateExiste(Mockito.anyLong())).thenReturn(true);

        // act - assert
        BasePrueba.assertThrows(() ->  serviceUpdateTicket.actualizarTicket(ticket), ExcepcionExistence.class,"No existe el seat");
    }


    @Test
    public void validateExistTest(){
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        Mockito.when(repositorioTicket.validateExiste(Mockito.any())).thenReturn(false);
        ServiceUpdateTicket serviceUpdateTicket = new ServiceUpdateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        Ticket ticket = new TicketTestDataBuilder().build();

        BasePrueba.assertThrows(() -> serviceUpdateTicket.actualizarTicket(ticket), ExcepcionExistenceTicket.class,"No existe el ticket");
    }




    @Test
    public void actualizarTicket(){
        LocalDate time = LocalDate.now().plusDays(2);
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = time.atTime(localTime);
        Ticket ticket = new TicketTestDataBuilder().build();

        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioTicket.validateExiste(Mockito.any())).thenReturn(true);
        Mockito.when(movieProjectorRepository.findbyMovieProjectorForId(Mockito.any())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(localDateTime.toLocalDate()).conHourMovie(localDateTime.toLocalTime()).build());
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(1L);
        ServiceUpdateTicket serviceUpdateTicket = new ServiceUpdateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);

        serviceUpdateTicket.actualizarTicket(ticket);

    }
}
