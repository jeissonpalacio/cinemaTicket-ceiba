package com.ceiba.boleto.testdatabuilder;

import com.ceiba.boleto.modelo.entidad.Boleto;

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
        this.idSeats = Arrays.asList(1,2);
    }

    public Boleto build(){
        return new Boleto(idClient,amount,idMovieProjector,idSeats);
    }

}
