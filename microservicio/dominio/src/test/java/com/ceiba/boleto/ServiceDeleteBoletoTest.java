package com.ceiba.boleto;

import com.ceiba.BasePrueba;
import com.ceiba.asientos.puerto.repositorio.RepositorioAsiento;
import com.ceiba.boleto.puerto.repositorio.RepositorioBoleto;
import com.ceiba.boleto.servicio.ServicioBorrarBoleto;
import com.ceiba.proyeccion_cine.puerto.repositorio.RepositorioProyeccionCine;
import com.ceiba.proyeccion_cine.servicio.testdatabuilder.MovieProjectorTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionExistenciaBoleto;
import com.ceiba.dominio.excepcion.ExcepcionTiempoDeCambio;
import com.ceiba.boleto.testdatabuilder.TicketTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceDeleteBoletoTest {

    @Test
    public void validateTimeLimitTest(){
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        ServicioBorrarBoleto servicioBorrarBoleto = new ServicioBorrarBoleto(repositorioBoleto, repositorioProyeccionCine, repositorioAsiento);
        LocalDate date = LocalDate.now();
        LocalTime sixThirty = LocalTime.now();
        Mockito.when(repositorioProyeccionCine.buscarProyeccionCinePorId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(date).conHourMovie(sixThirty).build());
        Mockito.when(repositorioBoleto.validarExistenciaBoleto(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioBoleto.obtenerBoleto(1L)).thenReturn(new TicketTestDataBuilder().build());
        BasePrueba.assertThrows(()-> servicioBorrarBoleto.ejecutar(1L), ExcepcionTiempoDeCambio.class,"El tiempo de cambio paso");
    }

    @Test
    public void validateExistTest(){
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        ServicioBorrarBoleto servicioBorrarBoleto = new ServicioBorrarBoleto(repositorioBoleto, repositorioProyeccionCine, repositorioAsiento);
        Mockito.when(repositorioBoleto.validarExistenciaBoleto(Mockito.anyLong())).thenReturn(false);
        BasePrueba.assertThrows(()-> servicioBorrarBoleto.ejecutar(1L), ExcepcionExistenciaBoleto.class,"No existe el ticket");

    }


    @Test
    public void obtenerTicketTest(){
        LocalDate date = LocalDate.now().plusDays(2);
        LocalTime sixThirty = LocalTime.now();
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        Mockito.when(repositorioBoleto.obtenerBoleto(1L)).thenReturn(new TicketTestDataBuilder().build());
        Mockito.when(repositorioProyeccionCine.buscarProyeccionCinePorId(Mockito.any())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(date).conHourMovie(sixThirty).build());
        Mockito.when(repositorioBoleto.validarExistenciaBoleto(Mockito.anyLong())).thenReturn(true);
        ServicioBorrarBoleto servicioBorrarBoleto = new ServicioBorrarBoleto(repositorioBoleto, repositorioProyeccionCine, repositorioAsiento);
        servicioBorrarBoleto.ejecutar(1L);
        Mockito.verify(repositorioBoleto).obtenerBoleto(1L);

    }

    @Test
    public void eliminarTicketTest(){
        LocalDate date = LocalDate.now().plusDays(2);
        LocalTime sixThirty = LocalTime.now();

        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        Mockito.when(repositorioProyeccionCine.buscarProyeccionCinePorId(Mockito.any())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(date).conHourMovie(sixThirty).build());
        Mockito.when(repositorioBoleto.validarExistenciaBoleto(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioBoleto.obtenerBoleto(1L)).thenReturn(new TicketTestDataBuilder().build());
        ServicioBorrarBoleto servicioBorrarBoleto = new ServicioBorrarBoleto(repositorioBoleto, repositorioProyeccionCine, repositorioAsiento);
        servicioBorrarBoleto.ejecutar(1L);
        Mockito.verify(repositorioBoleto).eliminarBoleto(1L);
    }


}
