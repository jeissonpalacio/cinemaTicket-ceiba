package com.ceiba.boleto;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDisponibilidad;
import com.ceiba.asientos.puerto.repositorio.RepositorioAsiento;
import com.ceiba.dominio.excepcion.ExcepcionExistenciaBoleto;
import com.ceiba.dominio.excepcion.ExcepcionTiempoDeCambio;
import com.ceiba.boleto.modelo.entidad.Boleto;
import com.ceiba.boleto.puerto.repositorio.RepositorioBoleto;
import com.ceiba.boleto.servicio.ServicioActualizarBoleto;
import com.ceiba.proyeccion_cine.puerto.repositorio.RepositorioProyeccionCine;
import com.ceiba.proyeccion_cine.servicio.testdatabuilder.MovieProjectorTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionExistencia;
import com.ceiba.boleto.testdatabuilder.TicketTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ServiceUpdateBoletoTest {

    @Test
    public void validateTimeLimitTest(){
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        ServicioActualizarBoleto servicioActualizarBoleto = new ServicioActualizarBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine);
        LocalDate time = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        Boleto boleto = new TicketTestDataBuilder().build();
        Mockito.when(repositorioProyeccionCine.buscarProyeccionCinePorId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(time).conHourMovie(localTime).build());
        Mockito.when(repositorioBoleto.validarExistenciaBoleto(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioAsiento.validarAsiento(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioAsiento.consultarDisponibilidad(Mockito.anyInt())).thenReturn(1L);

        BasePrueba.assertThrows(()-> servicioActualizarBoleto.actualizarTicket(boleto), ExcepcionTiempoDeCambio.class,"El tiempo de cambio paso");
    }

    @Test
    public void validarDisponibilidadTest(){
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        Mockito.when(repositorioAsiento.consultarDisponibilidad(Mockito.anyInt())).thenReturn(0L);
        ServicioActualizarBoleto servicioActualizarBoleto = new ServicioActualizarBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine);
        Boleto boleto = new TicketTestDataBuilder().build();
        Mockito.when(repositorioProyeccionCine.buscarProyeccionCinePorId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().build());
        Mockito.when(repositorioBoleto.validarExistenciaBoleto(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioAsiento.validarAsiento(Mockito.anyInt())).thenReturn(true);
        BasePrueba.assertThrows(()-> servicioActualizarBoleto.actualizarTicket(boleto), ExcepcionDisponibilidad.class,"La silla esta siendo reservada");


    }

    @Test
    public void validarExistenciaSeatTest(){
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        Mockito.when(repositorioAsiento.validarAsiento(Mockito.anyInt())).thenReturn(false);
        ServicioActualizarBoleto servicioActualizarBoleto = new ServicioActualizarBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine);
        Boleto boleto = new TicketTestDataBuilder().build();
        Mockito.when(repositorioBoleto.validarExistenciaBoleto(Mockito.anyLong())).thenReturn(true);

        // act - assert
        BasePrueba.assertThrows(() ->  servicioActualizarBoleto.actualizarTicket(boleto), ExcepcionExistencia.class,"No existe el seat");
    }


    @Test
    public void validateExistTest(){
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        Mockito.when(repositorioBoleto.validarExistenciaBoleto(Mockito.any())).thenReturn(false);
        ServicioActualizarBoleto servicioActualizarBoleto = new ServicioActualizarBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine);
        Boleto boleto = new TicketTestDataBuilder().build();

        BasePrueba.assertThrows(() -> servicioActualizarBoleto.actualizarTicket(boleto), ExcepcionExistenciaBoleto.class,"No existe el ticket");
    }




    @Test
    public void actualizarTicket(){
        LocalDate time = LocalDate.now().plusDays(2);
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = time.atTime(localTime);
        Boleto boleto = new TicketTestDataBuilder().build();

        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        Mockito.when(repositorioAsiento.validarAsiento(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioBoleto.validarExistenciaBoleto(Mockito.any())).thenReturn(true);
        Mockito.when(repositorioProyeccionCine.buscarProyeccionCinePorId(Mockito.any())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(localDateTime.toLocalDate()).conHourMovie(localDateTime.toLocalTime()).build());
        Mockito.when(repositorioAsiento.consultarDisponibilidad(Mockito.anyInt())).thenReturn(1L);
        ServicioActualizarBoleto servicioActualizarBoleto = new ServicioActualizarBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine);

        servicioActualizarBoleto.actualizarTicket(boleto);

    }
}
