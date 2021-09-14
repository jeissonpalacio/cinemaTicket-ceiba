package com.ceiba.ticket.excepcion;

public class ExcepcionExistenceTicket extends  RuntimeException{

    public ExcepcionExistenceTicket(String message){
        super(message);
    }
}
