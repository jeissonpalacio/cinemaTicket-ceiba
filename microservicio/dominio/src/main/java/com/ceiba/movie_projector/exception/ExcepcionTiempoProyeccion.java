package com.ceiba.movie_projector.exception;

public class ExcepcionTiempoProyeccion extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionTiempoProyeccion(String message){
        super(message);
    }
}
