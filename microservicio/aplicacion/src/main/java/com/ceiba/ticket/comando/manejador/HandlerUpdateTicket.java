package com.ceiba.ticket.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.ticket.comando.CommandTicket;
import com.ceiba.ticket.comando.fabrica.FabricTicket;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.servicio.ServiceUpdateTicket;
import org.springframework.stereotype.Component;

@Component
public class HandlerUpdateTicket implements ManejadorComando<CommandTicket> {
    private final FabricTicket fabricTicket;
    private final ServiceUpdateTicket serviceUpdateTicket;


    public HandlerUpdateTicket(FabricTicket fabricTicket,
                               ServiceUpdateTicket serviceUpdateTicket) {
        this.fabricTicket = fabricTicket;
        this.serviceUpdateTicket = serviceUpdateTicket;
    }

    @Override
    public void ejecutar(CommandTicket commandTicket) {
        Ticket ticket = fabricTicket.crear(commandTicket);
        serviceUpdateTicket.actualizarTicket(ticket);
    }
}
