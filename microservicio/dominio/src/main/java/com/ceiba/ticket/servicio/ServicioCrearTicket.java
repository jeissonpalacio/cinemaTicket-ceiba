package com.ceiba.ticket.servicio;

import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

public class ServicioCrearTicket {

    private final RepositorioTicket repositorioTicket;

    public ServicioCrearTicket(RepositorioTicket repositorioTicket){
        this.repositorioTicket = repositorioTicket;
    }

    public Long crearServicioTicket(Ticket ticket){
        return this.repositorioTicket.crearTicket(ticket);
    }
}
