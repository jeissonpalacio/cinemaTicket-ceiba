package com.ceiba.ticket.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.ticket.comando.ComandoTicket;
import com.ceiba.ticket.comando.fabrica.FabricaTicket;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.servicio.ServicioActualizarTicket;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarTicket  implements ManejadorComando<ComandoTicket> {
    private final FabricaTicket fabricaTicket;
    private final ServicioActualizarTicket servicioActualizarTicket;


    public ManejadorActualizarTicket(FabricaTicket fabricaTicket,
                                     ServicioActualizarTicket servicioActualizarTicket) {
        this.fabricaTicket = fabricaTicket;
        this.servicioActualizarTicket = servicioActualizarTicket;
    }

    @Override
    public void ejecutar(ComandoTicket comandoTicket) {
        Ticket ticket = fabricaTicket.crear(comandoTicket);
        servicioActualizarTicket.actualizarTicket(ticket);
    }
}
