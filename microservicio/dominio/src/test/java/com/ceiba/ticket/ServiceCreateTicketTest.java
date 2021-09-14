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
        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(false);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        Ticket ticket = new TicketTestDataBuilder().build();

        // act - assert
        BasePrueba.assertThrows(() -> serviceCreateTicket.ejecutar(ticket), ExcepcionExistence.class,"No existe el seat");

    }

    @Test
    public void validarExistenciaSeatTestSuccesful(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(1L);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        Ticket ticket = new TicketTestDataBuilder().build();
        serviceCreateTicket.ejecutar(ticket);
        Mockito.verify(repositorioSeat,Mockito.atLeastOnce()).validateSeat(1);
    }

    @Test
    public void validarDisponibilidadTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);

        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(0L);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        //BasePrueba.assertThrows(()-> serviceCreateTicket.validateAvailability(1), ExcepcionAvailability.class,"La silla esta siendo reservada");


    }

    @Test
    public void validarDisponibilidadTestSuccesful(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);

        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(1L);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        //serviceCreateTicket.validateAvailability(1);
        Mockito.verify(repositorioSeat,Mockito.atLeastOnce()).consultavailable(1);
    }


    @Test
    public void purchaseEnabledDatePastTest(){
        LocalDate date = LocalDate.now().minusDays(1);
        LocalTime sixThirty = LocalTime.now().minus(Duration.ofHours(1));
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        //BasePrueba.assertThrows(()-> serviceCreateTicket.purchaseEnabled(date,sixThirty), ExcepcionProjectionTime.class,"error de tiempo, la funcion ya paso");


    }

    @Test
    public void purchaseEnabledHoursTest(){
        LocalDate date = LocalDate.now();
        LocalTime sixThirty = LocalTime.now().plusMinutes(50);
        LocalDateTime localDate = date.atTime(sixThirty);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        //BasePrueba.assertThrows(()-> serviceCreateTicket.purchaseEnabled(localDate.toLocalDate(),localDate.toLocalTime()), ExcepcionProjectionTime.class,"error reserva antes de una hora");


    }

    @Test
    public void purchaseEnabledHoursSuccesfuTest(){
        LocalDate date = LocalDate.now().plusDays(2);
        LocalTime sixThirty = LocalTime.now().minus(Duration.ofMinutes(50));
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCreateTicket serviceCreateTickets = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        ServiceCreateTicket serviceCreateTicket = Mockito.spy(serviceCreateTickets);
        //serviceCreateTicket.purchaseEnabled(date,sixThirty);
        //Mockito.verify(serviceCreateTicket).purchaseEnabled(date,sixThirty);



    }
    @Test
    public void purchaseEnabledHoursMenorSuccesfulDaysTest(){
        LocalDate date = LocalDate.now();
        LocalTime sixThirty = LocalTime.now();
        LocalDateTime localDate = date.atTime(sixThirty).plusHours(1).plusMinutes(10);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCreateTicket serviceCreateTickets = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        ServiceCreateTicket serviceCreateTicket = Mockito.spy(serviceCreateTickets);
        //serviceCreateTicket.purchaseEnabled(localDate.toLocalDate(),localDate.toLocalTime());
        //Mockito.verify(serviceCreateTicket).purchaseEnabled(localDate.toLocalDate(),localDate.toLocalTime());



    }
    @Test
    public void purchaseEnabledHoursSuccesfulDaysTest(){
        LocalDate date = LocalDate.now().plusDays(1);
        LocalTime sixThirty = LocalTime.now().minus(Duration.ofMinutes(70));
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCreateTicket serviceCreateTickets = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        ServiceCreateTicket serviceCreateTicket = Mockito.spy(serviceCreateTickets);
        //serviceCreateTicket.purchaseEnabled(date,sixThirty);
        //Mockito.verify(serviceCreateTicket).purchaseEnabled(date,sixThirty);



    }


    @Test
    public void calculateHalfPricethursdayTest(){
        LocalDate date = LocalDate.of(2021, 9, 2);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        Double result = 7500.00;
        //Assert.assertEquals(serviceCreateTicket.calculateHalfPrice(date,15000.00),result);
    }
    @Test
    public void calculateHalfPriceTuesdayTest(){
        LocalDate date = LocalDate.of(2021, 9, 7);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        Double result = 7500.00;
        //Assert.assertEquals(serviceCreateTicket.calculateHalfPrice(date,15000.00),result);
    }
    @Test
    public void calculateHalfPriceDifferentDayTest(){
        LocalDate date = LocalDate.of(2021, 9, 3);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        Double result = 15000.00;
        //Assert.assertEquals(serviceCreateTicket.calculateHalfPrice(date,15000.00),result);
    }

    @Test
    public void crearServicioTicketTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepository movieProjectorRepository = Mockito.mock(MovieProjectorRepository.class);
        ServiceCreateTicket serviceCreateTicket = new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
        Mockito.when(repositorioSeat.validateSeat(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(1L);
        Mockito.when(movieProjectorRepository.findbyMovieProjectorForId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().build());
        Mockito.when(repositorioTicket.createTicket(Mockito.any())).thenReturn(1L);
        Ticket ticket = new TicketTestDataBuilder().build();
        Long resp = 1L;
        Assert.assertEquals(serviceCreateTicket.ejecutar(ticket),resp);

    }

}
