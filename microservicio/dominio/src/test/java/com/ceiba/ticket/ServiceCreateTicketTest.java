package com.ceiba.ticket;

import com.ceiba.BasePrueba;
import com.ceiba.movie_projector.exception.ExcepcionProjectionTime;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepository;
import com.ceiba.movie_projector.servicio.testdatabuilder.MovieProjectorTestDataBuilder;
import com.ceiba.seats.excepcion.ExcepcionAvailability;
import com.ceiba.seats.excepcion.ExcepcionExistence;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import com.ceiba.ticket.servicio.ServiceCalculateHalfPrice;
import com.ceiba.ticket.servicio.ServiceCreateTicket;
import com.ceiba.ticket.testdatabuilder.TicketTestDataBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ServiceCreateTicketTest {

    @Test
    public void validarExistenciaSeatTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCalculateHalfPrice serviceCalculateHalfPrice = Mockito.mock(ServiceCalculateHalfPrice.class);

        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(false);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository,serviceCalculateHalfPrice);
        Ticket ticket = new TicketTestDataBuilder().build();

        // act - assert
        BasePrueba.assertThrows(() -> serviceCreateTicket.ejecutar(ticket), ExcepcionExistence.class,"No existe el seat");

    }


    @Test
    public void validarDisponibilidadTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCalculateHalfPrice serviceCalculateHalfPrice = Mockito.mock(ServiceCalculateHalfPrice.class);

        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(0L);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository,serviceCalculateHalfPrice);
        Ticket ticket = new TicketTestDataBuilder().build();
        BasePrueba.assertThrows(()-> serviceCreateTicket.ejecutar(ticket), ExcepcionAvailability.class,"La silla esta siendo reservada");


    }


    @Test
    public void purchaseEnabledDatePastTest(){
        LocalDate date = LocalDate.now().minusDays(1);
        LocalTime sixThirty = LocalTime.now().minus(Duration.ofHours(1));
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCalculateHalfPrice serviceCalculateHalfPrice = Mockito.mock(ServiceCalculateHalfPrice.class);

        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(1L);
        Mockito.when(movieProjectorRepository.findbyMovieProjectorForId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(date).conHourMovie(sixThirty).build());
        Ticket ticket = new TicketTestDataBuilder().build();
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository,serviceCalculateHalfPrice);
        BasePrueba.assertThrows(()-> serviceCreateTicket.ejecutar(ticket), ExcepcionProjectionTime.class,"error de tiempo, la funcion ya paso");


    }


    @Test
    public void purchaseEnabledHoursTest(){
        LocalDate date = LocalDate.now();
        LocalTime sixThirty = LocalTime.now().plusMinutes(50);
        LocalDateTime localDate = date.atTime(sixThirty);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCalculateHalfPrice serviceCalculateHalfPrice = Mockito.mock(ServiceCalculateHalfPrice.class);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository,serviceCalculateHalfPrice);
        Mockito.when(movieProjectorRepository.findbyMovieProjectorForId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(localDate.toLocalDate()).conHourMovie(localDate.toLocalTime()).build());
        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(1L);
        Ticket ticket = new TicketTestDataBuilder().build();
        BasePrueba.assertThrows(()-> serviceCreateTicket.ejecutar(ticket), ExcepcionProjectionTime.class,"error reserva antes de una hora");


    }

    @Test
    public void purchaseEnabledDayTest(){
        LocalDate date = LocalDate.now();
        LocalTime sixThirty = LocalTime.now().plusMinutes(70);
        LocalDateTime localDate = date.atTime(sixThirty);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCalculateHalfPrice serviceCalculateHalfPrice = Mockito.mock(ServiceCalculateHalfPrice.class);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository,serviceCalculateHalfPrice);
        Mockito.when(movieProjectorRepository.findbyMovieProjectorForId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(localDate.toLocalDate()).conHourMovie(localDate.toLocalTime()).build());
        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(1L);
        Ticket ticket = new TicketTestDataBuilder().build();
        serviceCreateTicket.ejecutar(ticket);


    }

    @Test
    public void crearServicioTicketTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCalculateHalfPrice serviceCalculateHalfPrice = Mockito.mock(ServiceCalculateHalfPrice.class);

        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository,serviceCalculateHalfPrice);
        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(1L);
        Mockito.when(movieProjectorRepository.findbyMovieProjectorForId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().build());
        Mockito.when(repositorioTicket.createTicket(Mockito.any())).thenReturn(1L);
        Ticket ticket = new TicketTestDataBuilder().build();
        Long resp = 1L;
        Assert.assertEquals(serviceCreateTicket.ejecutar(ticket),resp);

    }

}
