package com.ceiba.ticket.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.ticket.comando.ComandoTicket;
import com.ceiba.ticket.comando.fabrica.FabricaTicket;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.servicio.ServiceCreateTicket;
import org.springframework.stereotype.Component;


@Component
public class ManejadorCrearTicket implements ManejadorComandoRespuesta<ComandoTicket, ComandoRespuesta<Long>> {

    private final FabricaTicket fabricaTicket;
    private final ServiceCreateTicket serviceCreateTicket;


    public ManejadorCrearTicket(FabricaTicket fabricaTicket, ServiceCreateTicket serviceCreateTicket){
        this.fabricaTicket = fabricaTicket;
        this.serviceCreateTicket = serviceCreateTicket;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoTicket comandoTicket) {
        Ticket ticket = this.fabricaTicket.crear(comandoTicket);
        Long idTicket = this.serviceCreateTicket.ejecutar(ticket);
        return new ComandoRespuesta<>(idTicket);
    }
}
