package com.ceiba.seats.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.movie_projector.controlador.ConsultaControladorMovieProjectorTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaControladorSeats.class)
public class ConsultaControladorSeatsTest {

}
