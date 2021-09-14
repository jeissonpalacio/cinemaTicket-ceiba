package com.ceiba.ticket.servicio.testdatabuilder;

import com.ceiba.ticket.comando.CommandTicket;

import java.util.Arrays;
import java.util.List;

public class ComandoTicketTestDataBuilder {

    private Long idTicket;
    private Integer idClient;
    private Double amount;
    private Integer idMovieProjector;
    private List<Integer> idSeats;

    public ComandoTicketTestDataBuilder(){
            this.idClient = 1;
            this.amount = 15000.00;
            this.idMovieProjector = 1;
            this.idSeats =  Arrays.asList(1);

    }
    public ComandoTicketTestDataBuilder conIdTicket(Long idTicke){
            this.idTicket = idTicke;
            return this;
    }

    public CommandTicket build(){
        return new CommandTicket(idTicket,idClient,amount,idMovieProjector,idSeats);
    }



}
