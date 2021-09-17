package com.ceiba.boleto.servicio;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ServicioCalcularDescuento {

    public Double ejecutar(LocalDate date, double precio){
        if(date.getDayOfWeek() == DayOfWeek.TUESDAY || DayOfWeek.THURSDAY == date.getDayOfWeek()){
            precio = precio/2;
        }

        return precio;
    }
}
