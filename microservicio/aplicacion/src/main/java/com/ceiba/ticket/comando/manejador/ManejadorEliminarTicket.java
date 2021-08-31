package com.ceiba.ticket.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.servicio.ServicioConsultaMovieProjector;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.servicio.ServicioEliminarTicket;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarTicket implements ManejadorComando<Long> {

    private final ServicioEliminarTicket servicioEliminarTicket;
    private final ServicioConsultaMovieProjector servicioConsultaMovieProjector;

    public ManejadorEliminarTicket(ServicioEliminarTicket servicioEliminarTicket,
                                   ServicioConsultaMovieProjector servicioConsultaMovieProjector
    ){
        this.servicioEliminarTicket = servicioEliminarTicket;
        this.servicioConsultaMovieProjector = servicioConsultaMovieProjector;
    }

    @Override
    public void ejecutar(Long idTicket) {
        // consultar el ticket
        Ticket ticket = this.servicioEliminarTicket.obtenerTicket(idTicket);
        MovieProjector movieProjector = this.servicioConsultaMovieProjector.findMovieProjectorById(ticket.getIdMovieProjector());
        this.servicioEliminarTicket.validateTimeLimit(movieProjector.getMovieProjection());
        this.servicioEliminarTicket.eliminarTicket(idTicket);
    }
}
