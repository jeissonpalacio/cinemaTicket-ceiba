package com.ceiba.seats.excepcion;

public class ExcepcionAvailability extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionAvailability(String mensaje){
        super(mensaje);
    }


}
