package com.ceiba.ticket.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.servicio.ServicioConsultaMovieProjector;
import com.ceiba.seats.servicio.ServicioActualizarSeat;
import com.ceiba.ticket.comando.ComandoTicket;
import com.ceiba.ticket.comando.fabrica.FabricaTicket;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.servicio.ServicioCrearTicket;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.stereotype.Component;


@Component
public class ManejadorCrearTicket implements ManejadorComandoRespuesta<ComandoTicket, ComandoRespuesta<Long>> {

    private final FabricaTicket fabricaTicket;
    private final ServicioCrearTicket servicioCrearTicket;
    private final ServicioActualizarSeat servicioActualizarSeat;
    private final ServicioConsultaMovieProjector servicioConsultaMovieProjector;


    public ManejadorCrearTicket(FabricaTicket fabricaTicket, ServicioCrearTicket servicioCrearTicket,
                                ServicioActualizarSeat servicioActualizarSeat,
                                ServicioConsultaMovieProjector servicioConsultaMovieProjector
    ){
        this.fabricaTicket = fabricaTicket;
        this.servicioCrearTicket = servicioCrearTicket;
        this.servicioActualizarSeat = servicioActualizarSeat;
        this.servicioConsultaMovieProjector = servicioConsultaMovieProjector;
    }


    // debo de crear un manejador y ingresar toda la logica de seat y llamar aqui en el manejador

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoTicket comandoTicket) {
        servicioActualizarSeat.validarCantidad(comandoTicket.getIdSeats());
        comandoTicket.getIdSeats().forEach(value->{
            servicioActualizarSeat.validarExistenciaSeat(value);
            servicioActualizarSeat.validarDisponibilidad(value);
        });
        MovieProjector movieProjector = servicioConsultaMovieProjector.findMovieProjectorById(comandoTicket.getIdMovieProjector());
        servicioConsultaMovieProjector.purchaseEnabled(movieProjector.getHourMovie());
        comandoTicket.setAmount(servicioConsultaMovieProjector.calculateHalfPrice(movieProjector.getMovieProjection(),comandoTicket.getAmount()));
        Ticket ticket = this.fabricaTicket.crear(comandoTicket);
        Long idTicket = this.servicioCrearTicket.crearServicioTicket(ticket);
        // Duda este for debe de ir aqui?
        comandoTicket.getIdSeats().forEach(value ->{
            servicioActualizarSeat.actualizarSeat(value,idTicket,0);
        });
        return new ComandoRespuesta<>(idTicket);
    }
}
