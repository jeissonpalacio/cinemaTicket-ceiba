package com.ceiba.seats.excepcion;

public class ExcepcionQuantity extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionQuantity(String mensaje) {
        super(mensaje);
    }
}
