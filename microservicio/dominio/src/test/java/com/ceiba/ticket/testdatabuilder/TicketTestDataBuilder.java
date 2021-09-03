package com.ceiba.ticket.testdatabuilder;

import com.ceiba.ticket.modelo.entidad.Ticket;

import java.util.Arrays;
import java.util.List;

public class TicketTestDataBuilder {

    Long idTicket;
    Integer idClient;
    Double amount;
    Integer idMovieProjector;
    List<Integer> idSeats;

    public TicketTestDataBuilder(){
        this.idTicket = 1L;
        this.idClient = 1;
        this.amount = 15000.00;
        this.idMovieProjector = 1;
        this.idSeats = Arrays.asList(1);
    }

    public Ticket build(){
        return new Ticket(idClient,amount,idMovieProjector,idSeats);
    }

}
