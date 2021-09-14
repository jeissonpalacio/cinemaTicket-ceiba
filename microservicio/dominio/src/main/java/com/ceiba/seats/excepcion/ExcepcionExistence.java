package com.ceiba.seats.excepcion;

public class ExcepcionExistence extends   RuntimeException{
    private static final long serialVersionUID = 1L;


    public ExcepcionExistence(String mensaje) {
        super(mensaje);
    }

}
