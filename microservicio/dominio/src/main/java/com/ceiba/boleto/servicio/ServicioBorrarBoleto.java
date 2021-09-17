package com.ceiba.boleto.servicio;

import com.ceiba.boleto.modelo.entidad.Boleto;
import com.ceiba.boleto.puerto.repositorio.RepositorioBoleto;
import com.ceiba.proyeccion_cine.modelo.entidad.ProyeccionCine;
import com.ceiba.proyeccion_cine.puerto.repositorio.RepositorioProyeccionCine;
import com.ceiba.asientos.puerto.repositorio.RepositorioAsiento;
import com.ceiba.dominio.excepcion.ExcepcionExistenciaBoleto;
import com.ceiba.dominio.excepcion.ExcepcionTiempoDeCambio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ServicioBorrarBoleto {
    private final RepositorioBoleto repositorioBoleto;
    private final RepositorioProyeccionCine repositorioProyeccionCine;
    private final RepositorioAsiento repositorioAsiento;

    private final String NO_EXISTE_EL_TICKET = "No existe el ticket";
    private final String EL_TIEMPO_DE_CAMBIO_PASO= "El tiempo de cambio paso";
    public ServicioBorrarBoleto(RepositorioBoleto repositorioBoleto,
                                RepositorioProyeccionCine repositorioProyeccionCine,
                                RepositorioAsiento repositorioAsiento
                                  ){
        this.repositorioBoleto = repositorioBoleto;
        this.repositorioProyeccionCine = repositorioProyeccionCine;
        this.repositorioAsiento = repositorioAsiento;
    }

    private void validarExistenciaBoleto(Long idBoleto){

        boolean exist = this.repositorioBoleto.validarExistenciaBoleto(idBoleto);
        if(!exist){
            throw new ExcepcionExistenciaBoleto(NO_EXISTE_EL_TICKET);
        }
    }
    private void validarTiempoLimiteParaEliminar(LocalDate date, LocalTime time){
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atTime(LocalTime.now());
        LocalDateTime localDateProjection = date.atTime(time);
        final long days = ChronoUnit.DAYS.between(localDateTime, localDateProjection);
        if(days<1){
            throw  new ExcepcionTiempoDeCambio(EL_TIEMPO_DE_CAMBIO_PASO);
        }
    }

    private Boleto obtenerTicket(Long id){
        validarExistenciaBoleto(id);
        return repositorioBoleto.obtenerBoleto(id);
    }

    public void ejecutar(Long id){
        Boleto boleto = obtenerTicket(id);
        ProyeccionCine proyeccionCine = this.repositorioProyeccionCine.buscarProyeccionCinePorId(boleto.getIdMovieProjector());
        validarTiempoLimiteParaEliminar(proyeccionCine.getMovieProjection(), proyeccionCine.getHourMovie());
        this.repositorioAsiento.actualizarDisponibilidadAsiento(id,null,1);
        this.repositorioBoleto.eliminarBoleto(id);
    }


}
