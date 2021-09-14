package com.ceiba.ticket.comando.fabrica;

import com.ceiba.ticket.comando.CommandTicket;
import com.ceiba.ticket.modelo.entidad.Ticket;
import org.springframework.stereotype.Component;

@Component
public class FabricTicket {

    public Ticket crear(CommandTicket commandTicket){

        if(commandTicket.getIdTicket()!=null){
            return new Ticket(commandTicket.getIdTicket(), commandTicket.getIdClient(),
                    commandTicket.getAmount(), commandTicket.getIdMovieProjector(), commandTicket.getIdSeats());

        }
         return new Ticket(commandTicket.getIdClient(), commandTicket.getAmount(),
                 commandTicket.getIdMovieProjector(), commandTicket.getIdSeats());
    }
}
