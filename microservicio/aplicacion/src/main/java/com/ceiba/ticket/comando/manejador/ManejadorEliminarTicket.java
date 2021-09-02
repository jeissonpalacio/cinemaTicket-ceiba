package com.ceiba.ticket.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.ticket.servicio.ServicioEliminarTicket;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarTicket implements ManejadorComando<Long> {

    private final ServicioEliminarTicket servicioEliminarTicket;

    public ManejadorEliminarTicket(ServicioEliminarTicket servicioEliminarTicket
    ){
        this.servicioEliminarTicket = servicioEliminarTicket;
    }

    @Override
    public void ejecutar(Long idTicket) {
        this.servicioEliminarTicket.eliminarTicket(idTicket);
    }
}
