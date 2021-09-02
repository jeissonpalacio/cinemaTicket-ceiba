package com.ceiba.ticket.servicio.testdatabuilder;

import com.ceiba.ticket.comando.ComandoTicket;
import com.ceiba.usuario.servicio.testdatabuilder.ComandoUsuarioTestDataBuilder;

import java.util.Arrays;
import java.util.List;

public class ComandoTicketTestDataBuilder {

    private Long idTicket;
    private Integer idClient;
    private Double amount;
    private Integer idMovieProjector;
    private List<Integer> idSeats;

    public ComandoTicketTestDataBuilder(){
            this.idTicket = 1L;
            this.idClient = 1;
            this.amount = 15000.00;
            this.idMovieProjector = 1;
            this.idSeats =  Arrays.asList(1);

    }

    public ComandoTicket build(){
        return new ComandoTicket(idTicket,idClient,amount,idMovieProjector,idSeats);
    }



}
