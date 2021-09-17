package com.ceiba.dominio.excepcion;

public class ExcepcionExistenciaBoleto extends  RuntimeException{

    public ExcepcionExistenciaBoleto(String message){
        super(message);
    }
}
