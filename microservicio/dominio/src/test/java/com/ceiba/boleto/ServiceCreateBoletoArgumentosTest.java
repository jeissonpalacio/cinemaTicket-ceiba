package com.ceiba.boleto;

import com.ceiba.asientos.puerto.repositorio.RepositorioAsiento;
import com.ceiba.boleto.modelo.entidad.Boleto;
import com.ceiba.boleto.puerto.repositorio.RepositorioBoleto;
import com.ceiba.boleto.servicio.ServicioCalcularDescuento;
import com.ceiba.boleto.servicio.ServicioCrearBoleto;
import com.ceiba.boleto.testdatabuilder.TicketTestDataBuilder;
import com.ceiba.proyeccion_cine.puerto.repositorio.RepositorioProyeccionCine;
import com.ceiba.proyeccion_cine.servicio.testdatabuilder.MovieProjectorTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ServiceCreateBoletoArgumentosTest {



    @Mock
    private RepositorioBoleto repositorioBoleto;

    @Mock
    private RepositorioAsiento repositorioAsiento;

    @Mock
    private RepositorioProyeccionCine repositorioProyeccionCine;

    @Spy
    private ServicioCalcularDescuento servicioCalcularDescuento;


    @Captor
    ArgumentCaptor<Boleto> boletoArgumentCaptor;

    @InjectMocks
    private ServicioCrearBoleto servicioCrearBoletoMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void crearServicioTicketCaptorTest(){
        Mockito.when(repositorioAsiento.validarAsiento(Mockito.anyInt())).thenReturn(true);
        Mockito.when(repositorioAsiento.consultarDisponibilidad(Mockito.anyInt())).thenReturn(1L);
        Mockito.when(repositorioProyeccionCine.buscarProyeccionCinePorId(Mockito.anyInt())).thenReturn(new MovieProjectorTestDataBuilder().build());
        Mockito.when(repositorioBoleto.crearBoleto(Mockito.any())).thenReturn(1L);
        Boleto boleto = new TicketTestDataBuilder().build();
        servicioCrearBoletoMock= new ServicioCrearBoleto(repositorioBoleto,repositorioAsiento,repositorioProyeccionCine,servicioCalcularDescuento);
        servicioCrearBoletoMock.ejecutar(boleto);
        Mockito.verify(repositorioBoleto).crearBoleto(boletoArgumentCaptor.capture());
        Boleto boletocaptorResult = boletoArgumentCaptor.getValue();

        Double result = 15000.00;
        assertEquals(result,boletocaptorResult.getAmount());
    }

}
