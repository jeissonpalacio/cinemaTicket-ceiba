package com.ceiba.ticket.servicio;

import com.ceiba.ticket.excepcion.ExcepcionExistenciaTicket;
import com.ceiba.ticket.excepcion.ExcepcionTiempoDeCambio;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

import java.time.LocalDate;
import java.time.Period;

public class ServicioEliminarTicket {
    private final RepositorioTicket repositorioTicket;

    private final String NO_EXISTE_EL_TICKET = "No existe el ticket";
    private final String EL_TIEMPO_DE_CAMBIO_PASO= "El tiempo de cambio paso";
    public ServicioEliminarTicket(RepositorioTicket repositorioTicket){
        this.repositorioTicket = repositorioTicket;
    }

    public void validateExist(Long idTicket){

        boolean exist = this.repositorioTicket.validarExiste(idTicket);
        if(!exist){
            throw new ExcepcionExistenciaTicket(NO_EXISTE_EL_TICKET);
        }
    }
    // Esta logica se coloca en otro servicio ServicioValidaciones o se conserva porque puede ser logicas diferentes
    public void validateTimeLimit(LocalDate time){
        Period period = Period.between(LocalDate.now(),time);
        if(period.getDays()<1){
            throw  new ExcepcionTiempoDeCambio(EL_TIEMPO_DE_CAMBIO_PASO);
        }
    }

    public void eliminarTicket(Long id){
        this.repositorioTicket.eliminarTicket(id);
    }

    public Ticket obtenerTicket(Long id){
        validateExist(id);
        return repositorioTicket.obtenerTicket(id);
    }

}
