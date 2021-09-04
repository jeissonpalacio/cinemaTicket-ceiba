package com.ceiba.movie_projector.controlador;

import com.ceiba.movie_projector.consulta.ManejadorListarMovieProjector;
import com.ceiba.movie_projector.modelo.dto.DtoMovieProjector;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movieprojector")
@Api(tags={"Controlador consulta movie projector"})
public class ConsultaControladorMovieProjector {

    ManejadorListarMovieProjector manejadorListarMovieProjector;

    @Autowired
    public ConsultaControladorMovieProjector(ManejadorListarMovieProjector manejadorListarMovieProjector){
        this.manejadorListarMovieProjector = manejadorListarMovieProjector;
    }

    @GetMapping
    @ApiOperation("Lista movie projector")
    public List<DtoMovieProjector> listarMovieProjector(){
        return this.manejadorListarMovieProjector.ejecutarListarMovieProjector();
    }


}
