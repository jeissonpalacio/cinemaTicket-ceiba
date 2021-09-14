package com.ceiba.ticket.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.ticket.comando.CommandTicket;
import com.ceiba.ticket.comando.fabrica.FabricTicket;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.servicio.ServiceCreateTicket;
import org.springframework.stereotype.Component;


@Component
public class HandlerCreateTicket implements ManejadorComandoRespuesta<CommandTicket, ComandoRespuesta<Long>> {

    private final FabricTicket fabricTicket;
    private final ServiceCreateTicket serviceCreateTicket;


    public HandlerCreateTicket(FabricTicket fabricTicket, ServiceCreateTicket serviceCreateTicket){
        this.fabricTicket = fabricTicket;
        this.serviceCreateTicket = serviceCreateTicket;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(CommandTicket commandTicket) {
        Ticket ticket = this.fabricTicket.crear(commandTicket);
        Long idTicket = this.serviceCreateTicket.ejecutar(ticket);
        return new ComandoRespuesta<>(idTicket);
    }
}
