package com.ceiba.ticket.comando.fabrica;

import com.ceiba.ticket.comando.ComandoTicket;
import com.ceiba.ticket.modelo.entidad.Ticket;
import org.springframework.stereotype.Component;

@Component
public class FabricaTicket {

    public Ticket crear(ComandoTicket comandoTicket){

        if(comandoTicket.getIdTicket()!=null){
            return new Ticket(comandoTicket.getIdTicket(),comandoTicket.getIdClient(),comandoTicket.getAmount(),comandoTicket.getIdMovieProjector());

        }
         return new Ticket(comandoTicket.getIdClient(),comandoTicket.getAmount(),comandoTicket.getIdMovieProjector());
    }
}
