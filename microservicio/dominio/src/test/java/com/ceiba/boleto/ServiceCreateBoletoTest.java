package com.ceiba.boleto;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDisponibilidad;
import com.ceiba.asientos.puerto.repositorio.RepositorioAsiento;
import com.ceiba.boleto.puerto.repositorio.RepositorioBoleto;
import com.ceiba.boleto.servicio.ServicioCalcularDescuento;
import com.ceiba.boleto.servicio.ServicioCrearBoleto;
import com.ceiba.dominio.excepcion.ExcepcionProyeccionTiempo;
import com.ceiba.proyeccion_cine.puerto.repositorio.RepositorioProyeccionCine;
import com.ceiba.proyeccion_cine.servicio.testdatabuilder.MovieProjectorTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionExistencia;
import com.ceiba.boleto.modelo.entidad.Boleto;
import com.ceiba.boleto.testdatabuilder.TicketTestDataBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ServiceCreateBoletoTest {

    @Test
    public void validarExistenciaSeatTest(){
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        ServicioCalcularDescuento servicioCalcularDescuento = Mockito.mock(ServicioCalcularDescuento.class);

        Mockito.when(repositorioAsiento.validarAsiento(Mockito.anyInt())).thenReturn(false);
        ServicioCrearBoleto servicioCrearBoleto = new ServicioCrearBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine, servicioCalcularDescuento);
        Boleto boleto = new TicketTestDataBuilder().build();

        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearBoleto.ejecutar(boleto), ExcepcionExistencia.class,"No existe el seat");

    }


    @Test
    public void validarDisponibilidadTest(){
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        ServicioCalcularDescuento servicioCalcularDescuento = Mockito.mock(ServicioCalcularDescuento.class);

        Mockito.when(repositorioAsiento.validarAsiento(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioAsiento.consultarDisponibilidad(Mockito.anyInt())).thenReturn(0L);
        ServicioCrearBoleto servicioCrearBoleto = new ServicioCrearBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine, servicioCalcularDescuento);
        Boleto boleto = new TicketTestDataBuilder().build();
        BasePrueba.assertThrows(()-> servicioCrearBoleto.ejecutar(boleto), ExcepcionDisponibilidad.class,"La silla esta siendo reservada");


    }


    @Test
    public void purchaseEnabledDatePastTest(){
        LocalDate date = LocalDate.now().minusDays(1);
        LocalTime sixThirty = LocalTime.now().minus(Duration.ofHours(1));
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        ServicioCalcularDescuento servicioCalcularDescuento = Mockito.mock(ServicioCalcularDescuento.class);

        Mockito.when(repositorioAsiento.validarAsiento(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioAsiento.consultarDisponibilidad(Mockito.anyInt())).thenReturn(1L);
        Mockito.when(repositorioProyeccionCine.buscarProyeccionCinePorId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(date).conHourMovie(sixThirty).build());
        Boleto boleto = new TicketTestDataBuilder().build();
        ServicioCrearBoleto servicioCrearBoleto = new ServicioCrearBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine, servicioCalcularDescuento);
        BasePrueba.assertThrows(()-> servicioCrearBoleto.ejecutar(boleto), ExcepcionProyeccionTiempo.class,"error de tiempo, la funcion ya paso");


    }


    @Test
    public void purchaseEnabledHoursTest(){
        LocalDate date = LocalDate.now();
        LocalTime sixThirty = LocalTime.now().plusMinutes(50);
        LocalDateTime localDate = date.atTime(sixThirty);
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        ServicioCalcularDescuento servicioCalcularDescuento = Mockito.mock(ServicioCalcularDescuento.class);
        ServicioCrearBoleto servicioCrearBoleto = new ServicioCrearBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine, servicioCalcularDescuento);
        Mockito.when(repositorioProyeccionCine.buscarProyeccionCinePorId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(localDate.toLocalDate()).conHourMovie(localDate.toLocalTime()).build());
        Mockito.when(repositorioAsiento.validarAsiento(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioAsiento.consultarDisponibilidad(Mockito.anyInt())).thenReturn(1L);
        Boleto boleto = new TicketTestDataBuilder().build();
        BasePrueba.assertThrows(()-> servicioCrearBoleto.ejecutar(boleto), ExcepcionProyeccionTiempo.class,"error reserva antes de una hora");


    }

    @Test
    public void purchaseEnabledDayTest(){
        LocalDate date = LocalDate.now();
        LocalTime sixThirty = LocalTime.now().plusMinutes(70);
        LocalDateTime localDate = date.atTime(sixThirty);
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        ServicioCalcularDescuento servicioCalcularDescuento = Mockito.mock(ServicioCalcularDescuento.class);
        ServicioCrearBoleto servicioCrearBoleto = new ServicioCrearBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine, servicioCalcularDescuento);
        Mockito.when(repositorioProyeccionCine.buscarProyeccionCinePorId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().conMovieProjection(localDate.toLocalDate()).conHourMovie(localDate.toLocalTime()).build());
        Mockito.when(repositorioAsiento.validarAsiento(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioAsiento.consultarDisponibilidad(Mockito.anyInt())).thenReturn(1L);
        Boleto boleto = new TicketTestDataBuilder().build();
        servicioCrearBoleto.ejecutar(boleto);


    }

    @Test
    public void crearServicioTicketTest(){
        RepositorioBoleto repositorioBoleto = Mockito.mock(RepositorioBoleto.class);
        RepositorioAsiento repositorioAsiento = Mockito.mock(RepositorioAsiento.class);
        RepositorioProyeccionCine repositorioProyeccionCine = Mockito.mock(RepositorioProyeccionCine.class);
        ServicioCalcularDescuento servicioCalcularDescuento = Mockito.mock(ServicioCalcularDescuento.class);

        ServicioCrearBoleto servicioCrearBoleto = new ServicioCrearBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine, servicioCalcularDescuento);
        Mockito.when(repositorioAsiento.validarAsiento(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioAsiento.consultarDisponibilidad(Mockito.anyInt())).thenReturn(1L);
        Mockito.when(repositorioProyeccionCine.buscarProyeccionCinePorId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().build());
        Mockito.when(repositorioBoleto.crearBoleto(Mockito.any())).thenReturn(1L);
        Boleto boleto = new TicketTestDataBuilder().build();
        Long resp = 1L;
        Assert.assertEquals(servicioCrearBoleto.ejecutar(boleto),resp);

    }



}
