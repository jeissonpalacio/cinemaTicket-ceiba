package com.ceiba.configuracion;

import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.movie_projector.servicio.ServicioConsultaMovieProjector;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.seats.servicio.ServicioActualizarSeat;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import com.ceiba.ticket.servicio.ServicioActualizarTicket;
import com.ceiba.ticket.servicio.ServicioCrearTicket;
import com.ceiba.ticket.servicio.ServicioEliminarTicket;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioConsultaMovieProjector servicioConsultaMovieProjector(MovieProjectorRepositorio movieProjectorRepositorio){
        return new ServicioConsultaMovieProjector(movieProjectorRepositorio);
    }

    @Bean
    public ServicioEliminarTicket servicioEliminarTicket(RepositorioTicket repositorioTicket){
        return new ServicioEliminarTicket(repositorioTicket);

    }
    @Bean
    public ServicioActualizarTicket servicioActualizarTicket(RepositorioTicket repositorioTicket){
        return new ServicioActualizarTicket(repositorioTicket);
    }

    @Bean
    public ServicioActualizarSeat servicioActualizarSeat(RepositorioSeat repositorioSeat){
        return new ServicioActualizarSeat(repositorioSeat);
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
    public ServicioCrearTicket servicioCrearTicket(RepositorioTicket repositorioTicket){
        return new ServicioCrearTicket(repositorioTicket);
    }
	

}
