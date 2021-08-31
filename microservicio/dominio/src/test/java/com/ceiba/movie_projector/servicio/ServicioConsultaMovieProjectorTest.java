package com.ceiba.movie_projector.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.movie_projector.servicio.testdatabuilder.MovieProjectorTestDataBuilder;
import com.ceiba.seats.excepcion.ExcepcionCantidad;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ServicioConsultaMovieProjectorTest {

    @Test
    public void calculateHalfPriceTest(){

        MovieProjector movieProjector = new MovieProjectorTestDataBuilder().build();
        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioConsultaMovieProjector servicioConsultaMovieProjector = new ServicioConsultaMovieProjector(movieProjectorRepositorio);
        // act - assert

        Double price =  servicioConsultaMovieProjector.calculateHalfPrice(movieProjector.getMovieProjection(),15000.00);
        Double result = 7500.00;
        Assert.assertEquals(price,result);

    }


    @Test
    public void validatePriceTest(){

        MovieProjectorRepositorio movieProjectorRepositorio = Mockito.mock(MovieProjectorRepositorio.class);
        ServicioConsultaMovieProjector servicioConsultaMovieProjector = new ServicioConsultaMovieProjector(movieProjectorRepositorio);
        // act - assert
        Double price = -100.00;
        BasePrueba.assertThrows(()->servicioConsultaMovieProjector.validatePrice(price), ExcepcionCantidad.class,"");

    }

}
