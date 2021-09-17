package com.ceiba.boleto.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDisponibilidad;
import com.ceiba.boleto.modelo.entidad.Boleto;
import com.ceiba.dominio.excepcion.ExcepcionProyeccionTiempo;
import com.ceiba.proyeccion_cine.modelo.entidad.ProyeccionCine;
import com.ceiba.proyeccion_cine.puerto.repositorio.RepositorioProyeccionCine;
import com.ceiba.dominio.excepcion.ExcepcionExistencia;
import com.ceiba.asientos.puerto.repositorio.RepositorioAsiento;
import com.ceiba.boleto.puerto.repositorio.RepositorioBoleto;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class ServicioCrearBoleto {

    private static final String NO_EXISTE_SEAT = "No existe el seat";
    private static final String LA_SILLA_ESTA_RESERVADA = "La silla esta siendo reservada";
    private static final String ERROR_DE_TIEMPO = "error de tiempo, la funcion ya paso";
    private static final String NO_SE_PUEDE_HACER_UNA_HORA_ANTES = "error reserva antes de una hora";

    private final RepositorioBoleto repositorioBoleto;
    private final RepositorioAsiento repositorioAsiento;
    private final RepositorioProyeccionCine repositorioProyeccionCine;
    private final ServicioCalcularDescuento servicioCalcularDescuento;
    public ServicioCrearBoleto(RepositorioBoleto repositorioBoleto, RepositorioAsiento repositorioAsiento,
                               RepositorioProyeccionCine repositorioProyeccionCine, ServicioCalcularDescuento servicioCalcularDescuento){
        this.repositorioBoleto = repositorioBoleto;
        this.repositorioAsiento = repositorioAsiento;
        this.repositorioProyeccionCine = repositorioProyeccionCine;
        this.servicioCalcularDescuento = servicioCalcularDescuento;
    }


    private void validarExistenciaAsiento(Integer idAsiento){
        boolean existe = repositorioAsiento.validarAsiento(idAsiento);
        if(!existe){
            throw new ExcepcionExistencia(NO_EXISTE_SEAT);
        }
    }
    private void validarDisponibilidad(Integer idAsiento){
        Long disponibilidad = repositorioAsiento.consultarDisponibilidad(idAsiento);
        if(disponibilidad==0){
            throw new ExcepcionDisponibilidad(LA_SILLA_ESTA_RESERVADA);
        }
    }
    private void tiempoDeCompraHabilitada(LocalDate fecha, LocalTime tiempo){
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atTime(LocalTime.now());
        LocalDateTime localDateProjection = fecha.atTime(tiempo);
        long minutes = ChronoUnit.MINUTES.between(localDateTime, localDateProjection);
        final long days = ChronoUnit.DAYS.between(localDate,localDateProjection);
        if(localDateTime.isAfter(localDateProjection)){
            throw new ExcepcionProyeccionTiempo(ERROR_DE_TIEMPO);
        }else if(days==0 && minutes<=60){
                throw new ExcepcionProyeccionTiempo(NO_SE_PUEDE_HACER_UNA_HORA_ANTES);
        }
    }

    public Long ejecutar(Boleto boleto){
        boleto.getIdSeats().forEach(e->{
            validarExistenciaAsiento(e);
            validarDisponibilidad(e);
        });
        ProyeccionCine proyeccionCine = this.repositorioProyeccionCine.buscarProyeccionCinePorId(boleto.getIdMovieProjector());
        tiempoDeCompraHabilitada(proyeccionCine.getMovieProjection(), proyeccionCine.getHourMovie());
        boleto.setAmount(servicioCalcularDescuento.ejecutar(proyeccionCine.getMovieProjection(), boleto.getAmount()));
        Long id = this.repositorioBoleto.crearBoleto(boleto);
        boleto.getIdSeats().forEach(value -> repositorioAsiento.actualizarAsiento(value,id,0));
        return id;
    }
}
