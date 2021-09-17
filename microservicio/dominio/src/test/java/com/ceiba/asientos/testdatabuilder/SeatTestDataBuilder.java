package com.ceiba.asientos.testdatabuilder;

import com.ceiba.asientos.modelo.entidad.Asiento;

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

    public Asiento build(){
        return  new Asiento(idSeat,numberSeat,available,idTicket);
    }

}
