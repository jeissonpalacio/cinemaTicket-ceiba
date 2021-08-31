package com.ceiba.movie_projector.exception;

import com.ceiba.seats.excepcion.ExcepcionCantidad;

public class ExcepcionDeCantidad extends  RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExcepcionDeCantidad(String message){
        super(message);
    }
}
