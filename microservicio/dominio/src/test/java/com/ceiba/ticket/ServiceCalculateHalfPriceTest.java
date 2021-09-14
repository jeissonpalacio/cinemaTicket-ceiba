package com.ceiba.ticket;

import com.ceiba.ticket.servicio.ServiceCalculateHalfPrice;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ServiceCalculateHalfPriceTest{


    @Test
    public void calculateHalfPricethursdayTest(){
        LocalDate date = LocalDate.of(2021, 9, 2);
        ServiceCalculateHalfPrice serviceCalculateHalfPrice = new ServiceCalculateHalfPrice();
        Double result = 7500.00;
        Assert.assertEquals(serviceCalculateHalfPrice.ejecutar(date,15000.00),result);
    }
    @Test
    public void calculateHalfPriceTuesdayTest(){
        LocalDate date = LocalDate.of(2021, 9, 7);
        Double result = 7500.00;
        ServiceCalculateHalfPrice serviceCalculateHalfPrice = new ServiceCalculateHalfPrice();
        Assert.assertEquals(serviceCalculateHalfPrice.ejecutar(date,15000.00),result);
    }
    @Test
    public void calculateHalfPriceDifferentDayTest(){
        LocalDate date = LocalDate.of(2021, 9, 3);
        ServiceCalculateHalfPrice serviceCalculateHalfPrice = new ServiceCalculateHalfPrice();
        Double result = 15000.00;
        Assert.assertEquals(serviceCalculateHalfPrice.ejecutar(date,15000.00),result);
    }

}
