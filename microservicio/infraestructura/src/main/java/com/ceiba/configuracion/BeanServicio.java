package com.ceiba.configuracion;

import com.ceiba.asientos.puerto.repositorio.RepositorioAsiento;
import com.ceiba.boleto.puerto.repositorio.RepositorioBoleto;
import com.ceiba.boleto.servicio.ServicioActualizarBoleto;
import com.ceiba.boleto.servicio.ServicioCalcularDescuento;
import com.ceiba.boleto.servicio.ServicioCrearBoleto;
import com.ceiba.proyeccion_cine.puerto.repositorio.RepositorioProyeccionCine;
import com.ceiba.boleto.servicio.ServicioBorrarBoleto;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {


    @Bean
    public ServicioCalcularDescuento serviceCalculateHalfPrice(){
        return new ServicioCalcularDescuento();
    }
    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioBorrarBoleto servicioEliminarTicket(RepositorioBoleto repositorioBoleto,
                                                       RepositorioProyeccionCine repositorioProyeccionCine,
                                                       RepositorioAsiento repositorioAsiento){
        return new ServicioBorrarBoleto(repositorioBoleto, repositorioProyeccionCine, repositorioAsiento);

    }
    @Bean
    public ServicioActualizarBoleto servicioActualizarTicket(RepositorioBoleto repositorioBoleto,
                                                             RepositorioAsiento repositorioAsiento,
                                                             RepositorioProyeccionCine repositorioProyeccionCine){
        return new ServicioActualizarBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioCrearBoleto servicioCrearTicket(RepositorioBoleto repositorioBoleto, RepositorioAsiento repositorioAsiento,
                                                   RepositorioProyeccionCine repositorioProyeccionCine, ServicioCalcularDescuento servicioCalcularDescuento){
        return new ServicioCrearBoleto(repositorioBoleto, repositorioAsiento, repositorioProyeccionCine, servicioCalcularDescuento);
    }
	

}
