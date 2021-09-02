package com.ceiba.ticket;

import com.ceiba.BasePrueba;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.seats.excepcion.ExcepcionCantidad;
import com.ceiba.seats.excepcion.ExcepcionDisponibilidad;
import com.ceiba.seats.excepcion.ExcepcionExistencia;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.excepcion.ExcepcionTiempoDeCambio;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import com.ceiba.ticket.servicio.ServicioActualizarTicket;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class ServicioActualizarTicketTest {

    @Test
    public void validateTimeLimitTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioActualizarTicket servicioActualizarTicket = new ServicioActualizarTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);
        LocalDate time = LocalDate.now();

        BasePrueba.assertThrows(()->servicioActualizarTicket.validateTimeLimit(time), ExcepcionTiempoDeCambio.class,"El tiempo de cambio paso");
    }

    @Test
    public void validarCantidadTest(){
        List<Integer> list = Collections.emptyList();
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioActualizarTicket servicioActualizarTicket = new ServicioActualizarTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);

        BasePrueba.assertThrows(()->servicioActualizarTicket.validarCantidad(list), ExcepcionCantidad.class,"Solo puede tener dos asientos");
    }


    @Test
    public void validarDisponibilidadTest(){
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(0L);
        ServicioActualizarTicket servicioActualizarTicket = new ServicioActualizarTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);
        BasePrueba.assertThrows(()->servicioActualizarTicket.validarDisponibilidad(1), ExcepcionDisponibilidad.class,"La silla esta siendo reservada");


    }

    @Test
    public void validarExistenciaSeatTest(){
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        Mockito.when(repositorioSeat.validarSeat(Mockito.anyInt())).thenReturn(false);
        ServicioActualizarTicket servicioActualizarTicket = new ServicioActualizarTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarTicket.validarExistenciaSeat(1), ExcepcionExistencia.class,"No existe el seat");
    }


}
