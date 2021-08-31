package com.ceiba.ticket.excepcion;

public class ExcepcionExistenciaTicket extends  RuntimeException{

    public ExcepcionExistenciaTicket(String message){
        super(message);
    }
}
