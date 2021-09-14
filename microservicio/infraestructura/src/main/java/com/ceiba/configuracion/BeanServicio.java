package com.ceiba.configuracion;

import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepository;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import com.ceiba.ticket.servicio.ServiceCalculateHalfPrice;
import com.ceiba.ticket.servicio.ServiceCreateTicket;
import com.ceiba.ticket.servicio.ServiceUpdateTicket;
import com.ceiba.ticket.servicio.ServiceDeleteTicket;
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


    @Bean
    public ServiceCalculateHalfPrice serviceCalculateHalfPrice(){
        return new ServiceCalculateHalfPrice();
    }
    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServiceDeleteTicket servicioEliminarTicket(RepositorioTicket repositorioTicket,
                                                      MovieProjectorRepository movieProjectorRepository,
                                                      RepositorioSeat repositorioSeat){
        return new ServiceDeleteTicket(repositorioTicket, movieProjectorRepository,repositorioSeat);

    }
    @Bean
    public ServiceUpdateTicket servicioActualizarTicket(RepositorioTicket repositorioTicket,
                                                        RepositorioSeat repositorioSeat,
                                                        MovieProjectorRepository movieProjectorRepository){
        return new ServiceUpdateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository);
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
    public ServiceCreateTicket servicioCrearTicket(RepositorioTicket repositorioTicket, RepositorioSeat repositorioSeat,
                                                   MovieProjectorRepository movieProjectorRepository, ServiceCalculateHalfPrice serviceCalculateHalfPrice){
        return new ServiceCreateTicket(repositorioTicket,repositorioSeat, movieProjectorRepository,serviceCalculateHalfPrice);
    }
	

}
