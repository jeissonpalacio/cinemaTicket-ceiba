package com.ceiba.ticket.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComando;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.servicio.ServicioConsultaMovieProjector;
import com.ceiba.seats.servicio.ServicioActualizarSeat;
import com.ceiba.ticket.comando.ComandoTicket;
import com.ceiba.ticket.comando.fabrica.FabricaTicket;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.servicio.ServicioActualizarTicket;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarTicket  implements ManejadorComando<ComandoTicket> {
    private final FabricaTicket fabricaTicket;
    private final ServicioActualizarTicket servicioActualizarTicket;
    private final ServicioActualizarSeat servicioActualizarSeat;
    private final ServicioConsultaMovieProjector servicioConsultaMovieProjector;


    public ManejadorActualizarTicket(FabricaTicket fabricaTicket,
                                     ServicioActualizarTicket servicioActualizarTicket,
                                     ServicioActualizarSeat servicioActualizarSeat,
                                     ServicioConsultaMovieProjector servicioConsultaMovieProjector
                                     ) {
        this.fabricaTicket = fabricaTicket;
        this.servicioActualizarTicket = servicioActualizarTicket;
        this.servicioActualizarSeat = servicioActualizarSeat;
        this.servicioConsultaMovieProjector = servicioConsultaMovieProjector;
    }

    @Override
    public void ejecutar(ComandoTicket comandoTicket) {
        servicioActualizarSeat.validarCantidad(comandoTicket.getIdSeats());
        /**
         * Se debe ejecutar esta logica por separado porque antes de esto
         * debe de tener disponibilidad de silla
         * actualizar ticket de lo contrario se debe ejecutar el tiempo limite
         * o se agrupa toda la logica en un metodo
         *
         * Duda o todo debe ir en el servicio?
         */
        comandoTicket.getIdSeats().forEach(value->{
            servicioActualizarSeat.validarExistenciaSeat(value);
            servicioActualizarSeat.validarDisponibilidad(value);
        });
        Ticket ticket = this.fabricaTicket.crear(comandoTicket);
        MovieProjector movieProjector = servicioConsultaMovieProjector.findMovieProjectorById(comandoTicket.getIdMovieProjector());
        this.servicioActualizarTicket.validateTimeLimit(movieProjector.getMovieProjection());
        this.servicioActualizarSeat.actualizarSeatDisponible(ticket.getIdTicket());
        comandoTicket.getIdSeats().forEach(value ->{
            servicioActualizarSeat.actualizarSeat(value,ticket.getIdTicket(),0);
        });
        servicioActualizarTicket.actualizarTicket(ticket);



    }
}
