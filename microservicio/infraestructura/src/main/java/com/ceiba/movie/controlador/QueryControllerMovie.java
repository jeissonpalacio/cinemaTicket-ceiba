package com.ceiba.movie.controlador;


import com.ceiba.movie.consulta.HandlerListMovies;
import com.ceiba.movie.modelo.dto.DtoMovie;
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
public class QueryControllerMovie {

    private final HandlerListMovies handlerListMovies;

   @Autowired
   public QueryControllerMovie(HandlerListMovies handlerListMovies){
       this.handlerListMovies = handlerListMovies;
   }


    @GetMapping
    @ApiOperation("Listar peliculas")
    public List<DtoMovie> listar(){
        return this.handlerListMovies.executeList();
    };

}
