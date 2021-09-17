package com.ceiba.asientos.modelo.entidad;

import lombok.Getter;

@Getter
public class Asiento {


    Integer idSeat;
    String numberSeat;
    Integer available;
    Integer idTicket;


    public Asiento(Integer idSeat, String numberSeat, Integer available, Integer idTicket){

        this.idSeat = idSeat;
        this.numberSeat = numberSeat;
        this.available = available;
        this.idTicket = idTicket;


    }
}
