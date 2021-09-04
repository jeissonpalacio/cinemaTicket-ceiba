package com.ceiba.movie.controlador;


import com.ceiba.movie.consulta.ManejadorListarMovies;
import com.ceiba.movie.modelo.dto.DtoMovie;
import com.ceiba.usuario.consulta.ManejadorListarUsuarios;
import com.ceiba.usuario.controlador.ConsultaControladorUsuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Api(tags = {"Controlador consulta movie"})
public class ConsultaControladorMovie {

    private final ManejadorListarMovies manejadorListarMovies;

   @Autowired
   public ConsultaControladorMovie(ManejadorListarMovies manejadorListarMovies){
       this.manejadorListarMovies = manejadorListarMovies;
   }


    @GetMapping
    @ApiOperation("Listar peliculas")
    public List<DtoMovie> listar(){
        return this.manejadorListarMovies.ejecutarListar();
    };

}
