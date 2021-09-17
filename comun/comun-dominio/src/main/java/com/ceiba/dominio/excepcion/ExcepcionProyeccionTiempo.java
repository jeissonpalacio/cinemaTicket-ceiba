package com.ceiba.dominio.excepcion;

public class ExcepcionProyeccionTiempo extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionProyeccionTiempo(String message){
        super(message);
    }
}
