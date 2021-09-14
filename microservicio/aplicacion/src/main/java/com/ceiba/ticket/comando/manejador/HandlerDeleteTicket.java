package com.ceiba.ticket.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.ticket.servicio.ServiceDeleteTicket;
import org.springframework.stereotype.Component;

@Component
public class HandlerDeleteTicket implements ManejadorComando<Long> {

    private final ServiceDeleteTicket serviceDeleteTicket;

    public HandlerDeleteTicket(ServiceDeleteTicket serviceDeleteTicket
    ){
        this.serviceDeleteTicket = serviceDeleteTicket;
    }

    @Override
    public void ejecutar(Long idTicket) {
        this.serviceDeleteTicket.eliminarTicket(idTicket);
    }
}
