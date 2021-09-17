package com.ceiba.boleto;

import com.ceiba.boleto.servicio.ServicioCalcularDescuento;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ServicioCalcularDescuentoTest {


    @Test
    public void calculateHalfPricethursdayTest(){
        LocalDate date = LocalDate.of(2021, 9, 2);
        ServicioCalcularDescuento servicioCalcularDescuento = new ServicioCalcularDescuento();
        Double result = 7500.00;
        Assert.assertEquals(servicioCalcularDescuento.ejecutar(date,15000.00),result);
    }
    @Test
    public void calculateHalfPriceTuesdayTest(){
        LocalDate date = LocalDate.of(2021, 9, 7);
        Double result = 7500.00;
        ServicioCalcularDescuento servicioCalcularDescuento = new ServicioCalcularDescuento();
        Assert.assertEquals(servicioCalcularDescuento.ejecutar(date,15000.00),result);
    }
    @Test
    public void calculateHalfPriceDifferentDayTest(){
        LocalDate date = LocalDate.of(2021, 9, 3);
        ServicioCalcularDescuento servicioCalcularDescuento = new ServicioCalcularDescuento();
        Double result = 15000.00;
        Assert.assertEquals(servicioCalcularDescuento.ejecutar(date,15000.00),result);
    }

}
