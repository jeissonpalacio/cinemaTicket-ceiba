package com.ceiba.pelicula.controlador;


import com.ceiba.pelicula.consulta.ManejadorListarPeliculas;
import com.ceiba.pelicula.modelo.dto.DtoPelicula;
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
public class ConsultaControladorPelicula {

    private final ManejadorListarPeliculas manejadorListarPeliculas;

   @Autowired
   public ConsultaControladorPelicula(ManejadorListarPeliculas manejadorListarPeliculas){
       this.manejadorListarPeliculas = manejadorListarPeliculas;
   }


    @GetMapping
    @ApiOperation("Listar peliculas")
    public List<DtoPelicula> listar(){
        return this.manejadorListarPeliculas.ejecutar();
    }

}
