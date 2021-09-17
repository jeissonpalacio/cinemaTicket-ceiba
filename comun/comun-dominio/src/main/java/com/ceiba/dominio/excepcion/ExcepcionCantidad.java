package com.ceiba.dominio.excepcion;

public class ExcepcionCantidad extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionCantidad(String mensaje) {
        super(mensaje);
    }
}
