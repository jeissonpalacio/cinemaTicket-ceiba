package com.ceiba.dominio.excepcion;

public class ExcepcionDisponibilidad extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionDisponibilidad(String mensaje){
        super(mensaje);
    }


}
