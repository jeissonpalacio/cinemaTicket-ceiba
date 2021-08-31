package com.ceiba.seats.servicio;

import com.ceiba.seats.excepcion.ExcepcionCantidad;
import com.ceiba.seats.excepcion.ExcepcionDisponibilidad;
import com.ceiba.seats.excepcion.ExcepcionExistencia;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;

import java.util.List;

public class ServicioActualizarSeat {

    private static final String SOLO_PUEDE_TENER_DOS_ASIENTOS = "Solo puede tener dos asientos";
    private static final String NO_EXISTE_SEAT = "No existe el seat";
    private static final String LA_SILLA_ESTA_RESERVADA = "La silla esta siendo reservada";
    private final RepositorioSeat repositorioSeat;

    public ServicioActualizarSeat(RepositorioSeat repositorioSeat){
        this.repositorioSeat = repositorioSeat;
    }


    public void actualizarSeat(Integer idSeat, Long idTicket,Integer available){
         this.repositorioSeat.actualizarSeat(idSeat,idTicket,available);
    }

    public void validarExistenciaSeat(Integer idSeat){
        boolean existe = repositorioSeat.validarSeat(idSeat);
        if(!existe){
            throw new ExcepcionExistencia(NO_EXISTE_SEAT);
        }
    }
    // duda si validaciones de como esta debe de ir en el manejador o en el servicio
    public void validarCantidad(List<Integer> listId){
         boolean existe = listId.size()>0 && listId.size()<=2;
         if(!existe){
             throw new ExcepcionCantidad(SOLO_PUEDE_TENER_DOS_ASIENTOS);
         }
    };

    public void validarDisponibilidad(Integer idSeat){
        Long disponibilidad = repositorioSeat.consultavailable(idSeat);
        if(disponibilidad==0){
            throw new ExcepcionDisponibilidad(LA_SILLA_ESTA_RESERVADA);
        }
    }

    public void actualizarSeatDisponible(Long idTicket){
        this.repositorioSeat.actualizarSeatAvailable(idTicket,1);
    }

}
