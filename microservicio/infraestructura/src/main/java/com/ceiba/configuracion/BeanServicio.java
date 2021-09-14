package com.ceiba.configuracion;

import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepositorio;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BeanServicio {

    // Otra opcion?
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET","POST","PUT","DELETE");
            }
        };
    }

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarTicket servicioEliminarTicket(RepositorioTicket repositorioTicket,
                                                         MovieProjectorRepositorio movieProjectorRepositorio,
                                                         RepositorioSeat repositorioSeat){
        return new ServicioEliminarTicket(repositorioTicket,movieProjectorRepositorio,repositorioSeat);

    }
    @Bean
    public ServicioActualizarTicket servicioActualizarTicket(RepositorioTicket repositorioTicket,
                                                             RepositorioSeat repositorioSeat,
                                                             MovieProjectorRepositorio movieProjectorRepositorio){
        return new ServicioActualizarTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);
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
    public ServicioCrearTicket servicioCrearTicket(RepositorioTicket repositorioTicket,RepositorioSeat repositorioSeat,
                                                   MovieProjectorRepositorio movieProjectorRepositorio){
        return new ServicioCrearTicket(repositorioTicket,repositorioSeat,movieProjectorRepositorio);
    }
	

}
