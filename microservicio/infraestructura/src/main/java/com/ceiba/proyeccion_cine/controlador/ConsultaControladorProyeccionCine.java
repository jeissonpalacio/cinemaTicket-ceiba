package com.ceiba.proyeccion_cine.controlador;

import com.ceiba.proyeccion_cine.consulta.ManejadorListarProyeccionCine;
import com.ceiba.proyeccion_cine.consulta.ManejadorListarProyeccionCinePorId;
import com.ceiba.proyeccion_cine.consulta.ManejadorListarProyeccionCinePorIdPelicula;
import com.ceiba.proyeccion_cine.modelo.dto.DtoProyeccionCine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieprojector")
@Api(tags={"Controlador consulta movie projector"})
public class ConsultaControladorProyeccionCine {

    ManejadorListarProyeccionCine manejadorListarProyeccionCine;
    ManejadorListarProyeccionCinePorIdPelicula manejadorListarProyeccionCinePorIdPelicula;
    ManejadorListarProyeccionCinePorId manejadorListarProyeccionCinePorId;
    @Autowired
    public ConsultaControladorProyeccionCine(ManejadorListarProyeccionCine manejadorListarProyeccionCine,
                                             ManejadorListarProyeccionCinePorIdPelicula manejadorListarProyeccionCinePorIdPelicula,
                                             ManejadorListarProyeccionCinePorId manejadorListarProyeccionCinePorId){
        this.manejadorListarProyeccionCine = manejadorListarProyeccionCine;
        this.manejadorListarProyeccionCinePorIdPelicula = manejadorListarProyeccionCinePorIdPelicula;
        this.manejadorListarProyeccionCinePorId = manejadorListarProyeccionCinePorId;
    }

    @GetMapping
    @ApiOperation("Lista movie projector")
    public List<DtoProyeccionCine> listarMovieProjector(){
        return this.manejadorListarProyeccionCine.ejecutar();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Lista movie projector")
    public List<DtoProyeccionCine> movieProjectorsForId(@PathVariable Long id){
        return this.manejadorListarProyeccionCinePorIdPelicula.ejecutar(id);
    }

    @GetMapping(value = "/movie-projector-id/{id}")
    public DtoProyeccionCine getMovieProjector(@PathVariable Long id){
        return this.manejadorListarProyeccionCinePorId.ejecutar(id);
    }


}
