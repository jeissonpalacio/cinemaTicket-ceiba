package com.ceiba.ticket;

import com.ceiba.BasePrueba;
import com.ceiba.ticket.excepcion.ExcepcionTiempoDeCambio;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import com.ceiba.ticket.servicio.ServicioActualizarTicket;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class ServicioActualizarTicketTest {

    @Test
    public void validateTimeLimitTest(){
        RepositorioTicket repositorioTicket = Mockito.mock(RepositorioTicket.class);
        ServicioActualizarTicket servicioActualizarSeat = new ServicioActualizarTicket(repositorioTicket);
        LocalDate time = LocalDate.now();

        BasePrueba.assertThrows(()->servicioActualizarSeat.validateTimeLimit(time), ExcepcionTiempoDeCambio.class,"El tiempo de cambio paso");
    }
}
