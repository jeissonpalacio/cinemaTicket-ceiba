package com.ceiba.ticket.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.ticket.comando.ComandoTicket;
import com.ceiba.ticket.comando.fabrica.FabricaTicket;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.servicio.ServiceUpdateTicket;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarTicket  implements ManejadorComando<ComandoTicket> {
    private final FabricaTicket fabricaTicket;
    private final ServiceUpdateTicket serviceUpdateTicket;


    public ManejadorActualizarTicket(FabricaTicket fabricaTicket,
                                     ServiceUpdateTicket serviceUpdateTicket) {
        this.fabricaTicket = fabricaTicket;
        this.serviceUpdateTicket = serviceUpdateTicket;
    }

    @Override
    public void ejecutar(ComandoTicket comandoTicket) {
        Ticket ticket = fabricaTicket.crear(comandoTicket);
        serviceUpdateTicket.actualizarTicket(ticket);
    }
}
