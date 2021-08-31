package com.ceiba.ticket.servicio;

import com.ceiba.ticket.excepcion.ExcepcionTiempoDeCambio;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

import java.time.LocalDate;
import java.time.Period;

public class ServicioActualizarTicket {

    private final RepositorioTicket repositorioTicket;
    private final String EL_TIEMPO_DE_CAMBIO_PASO= "El tiempo de cambio paso";

    public ServicioActualizarTicket(RepositorioTicket repositorioTicket){
        this.repositorioTicket = repositorioTicket;

    }
    public void validateTimeLimit(LocalDate time){
        Period period = Period.between(LocalDate.now(),time);
        if(period.getDays()<1){
            throw  new ExcepcionTiempoDeCambio(EL_TIEMPO_DE_CAMBIO_PASO);
        }
    }


    public void actualizarTicket(Ticket ticket){
        repositorioTicket.cambiarProyeccionTicket(ticket);
    }

}
