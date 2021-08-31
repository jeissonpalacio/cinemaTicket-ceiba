package com.ceiba.seats.testdatabuilder;

import com.ceiba.seats.modelo.entidad.Seat;

public class SeatTestDataBuilder {

    Integer idSeat;
    String numberSeat;
    Integer available;
    Integer idTicket;

    public SeatTestDataBuilder(){
        this.idSeat = 1;
        this.numberSeat = "A1";
        this.available = 0;
        this.idTicket = 1;

    }

    public Seat build(){
        return  new Seat(idSeat,numberSeat,available,idTicket);
    }

}
