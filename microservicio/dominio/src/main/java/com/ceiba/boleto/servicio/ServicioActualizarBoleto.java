package com.ceiba.boleto.servicio;

import com.ceiba.dominio.excepcion.ExcepcionExistencia;
import com.ceiba.asientos.puerto.repositorio.RepositorioAsiento;
import com.ceiba.dominio.excepcion.ExcepcionExistenciaBoleto;
import com.ceiba.boleto.modelo.entidad.Boleto;
import com.ceiba.boleto.puerto.repositorio.RepositorioBoleto;
import com.ceiba.proyeccion_cine.modelo.entidad.ProyeccionCine;
import com.ceiba.proyeccion_cine.puerto.repositorio.RepositorioProyeccionCine;
import com.ceiba.dominio.excepcion.ExcepcionDisponibilidad;
import com.ceiba.dominio.excepcion.ExcepcionTiempoDeCambio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ServicioActualizarBoleto {

    private final RepositorioBoleto repositorioBoleto;
    private final RepositorioAsiento repositorioAsiento;
    private final RepositorioProyeccionCine repositorioProyeccionCine;
    private static final String EL_TIEMPO_DE_CAMBIO_PASO= "El tiempo de cambio paso";
    private static final String NO_EXISTE_SEAT = "No existe el seat";
    private static final String LA_SILLA_ESTA_RESERVADA = "La silla esta siendo reservada";
    private static final String NO_EXISTE_EL_TICKET = "No existe el ticket";


    public ServicioActualizarBoleto(RepositorioBoleto repositorioBoleto, RepositorioAsiento repositorioAsiento,
                                    RepositorioProyeccionCine repositorioProyeccionCine){
        this.repositorioBoleto = repositorioBoleto;
        this.repositorioAsiento = repositorioAsiento;
        this.repositorioProyeccionCine = repositorioProyeccionCine;

    }
    private void validarLimiteDeTiempo(LocalDate date, LocalTime time){
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atTime(LocalTime.now());
        LocalDateTime localDateProjection = date.atTime(time);
        final long days = ChronoUnit.DAYS.between(localDateTime, localDateProjection);
        if(days<1){
            throw  new ExcepcionTiempoDeCambio(EL_TIEMPO_DE_CAMBIO_PASO);
        }
    }
    private void validarDisponibilidad(Integer idSeat){
        Long disponibilidad = repositorioAsiento.consultarDisponibilidad(idSeat);
        if(disponibilidad==0){
            throw new ExcepcionDisponibilidad(LA_SILLA_ESTA_RESERVADA);
        }
    }
    private void validarExistenciaBoleto(Long idTicket){

        boolean exist = this.repositorioBoleto.validarExistenciaBoleto(idTicket);
        if(!exist){
            throw new ExcepcionExistenciaBoleto(NO_EXISTE_EL_TICKET);
        }
    }
    private void validarExistenciaDeAsiento(Integer idBoleto){
        boolean existe = repositorioAsiento.validarAsiento(idBoleto);
        if(!existe){
            throw new ExcepcionExistencia(NO_EXISTE_SEAT);
        }
    }
    public void actualizarTicket(Boleto boleto){
        validarExistenciaBoleto(boleto.getIdTicket());
        boleto.getIdSeats().forEach(value->{
            validarExistenciaDeAsiento(value);
            validarDisponibilidad(value);
        });
        ProyeccionCine proyeccionCine = this.repositorioProyeccionCine.buscarProyeccionCinePorId(boleto.getIdMovieProjector());
        validarLimiteDeTiempo(proyeccionCine.getMovieProjection(), proyeccionCine.getHourMovie());

        this.repositorioAsiento.actualizarDisponibilidadAsiento(boleto.getIdTicket(),null,1);
        boleto.getIdSeats().forEach(value -> repositorioAsiento.actualizarAsiento(value, boleto.getIdTicket(),0));
        repositorioBoleto.cambiarProyeccionBoleto(boleto);
    }

}
