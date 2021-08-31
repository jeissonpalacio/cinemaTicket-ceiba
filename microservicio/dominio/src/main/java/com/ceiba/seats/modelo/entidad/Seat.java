package com.ceiba.seats.modelo.entidad;

import lombok.Getter;

@Getter
public class Seat {


    Integer idSeat;
    String numberSeat;
    Integer available;
    Integer idTicket;


    public Seat(Integer idSeat,String numberSeat,Integer available, Integer idTicket){

        this.idSeat = idSeat;
        this.numberSeat = numberSeat;
        this.available = available;
        this.idTicket = idTicket;


    }
}
