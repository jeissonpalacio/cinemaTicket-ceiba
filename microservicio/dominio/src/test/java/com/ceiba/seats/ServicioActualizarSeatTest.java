package com.ceiba.seats;

import com.ceiba.BasePrueba;
import com.ceiba.seats.excepcion.ExcepcionCantidad;
import com.ceiba.seats.excepcion.ExcepcionDisponibilidad;
import com.ceiba.seats.excepcion.ExcepcionExistencia;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.seats.servicio.ServicioActualizarSeat;

import org.junit.Test;
import org.mockito.Mockito;


import java.util.Collections;
import java.util.List;

public class ServicioActualizarSeatTest {

    @Test
    public void validarCantidadTest(){
        List<Integer> list = Collections.emptyList();
        System.out.println(list.size());
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        ServicioActualizarSeat servicioActualizarSeat = new ServicioActualizarSeat(repositorioSeat);
        BasePrueba.assertThrows(()->servicioActualizarSeat.validarCantidad(list), ExcepcionCantidad.class,"Solo puede tener dos asientos");
    }
    @Test
    public void validarExistenciaSeatTest(){

        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        Mockito.when(repositorioSeat.validarSeat(Mockito.anyInt())).thenReturn(false);
        ServicioActualizarSeat servicioActualizarSeat = new ServicioActualizarSeat(repositorioSeat);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarSeat.validarExistenciaSeat(1), ExcepcionExistencia.class,"No existe el seat");
    }

    @Test
    public void validarDisponibilidadTest(){
        RepositorioSeat repositorioSeat = Mockito.mock(RepositorioSeat.class);
        Mockito.when(repositorioSeat.consultavailable(Mockito.anyInt())).thenReturn(0L);
        ServicioActualizarSeat servicioActualizarSeat = new ServicioActualizarSeat(repositorioSeat);
        BasePrueba.assertThrows(()->servicioActualizarSeat.validarDisponibilidad(1), ExcepcionDisponibilidad.class,"La silla esta siendo reservada");


    }

}

