package com.ceiba.ticket.servicio;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ServiceCalculateHalfPrice {

    public Double ejecutar(LocalDate date, double price){
        if(date.getDayOfWeek() == DayOfWeek.TUESDAY || DayOfWeek.THURSDAY == date.getDayOfWeek()){
            price = price/2;
        }

        return price;
    }
}
