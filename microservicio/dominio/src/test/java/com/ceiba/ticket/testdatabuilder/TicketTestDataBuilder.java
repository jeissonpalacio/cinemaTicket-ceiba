package com.ceiba.ticket.testdatabuilder;

import com.ceiba.ticket.modelo.entidad.Ticket;

public class TicketTestDataBuilder {

    Long idTicket;
    Integer idClient;
    Double amount;
    Integer idMovieProjector;

    public TicketTestDataBuilder(){
        this.idTicket = 1L;
        this.idClient = 1;
        this.amount = 15000.00;
        this.idMovieProjector = 1;
    }

    public Ticket build(){
        return new Ticket(idTicket,idClient,amount,idMovieProjector);
    }

}
