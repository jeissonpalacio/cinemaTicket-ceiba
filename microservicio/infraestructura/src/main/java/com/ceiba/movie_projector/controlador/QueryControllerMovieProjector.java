package com.ceiba.movie_projector.controlador;

import com.ceiba.movie_projector.consulta.HandlerListMovieProjector;
import com.ceiba.movie_projector.consulta.HandlerListMovieProjectorById;
import com.ceiba.movie_projector.consulta.HandlerListMovieProjectorByIdMovieProjector;
import com.ceiba.movie_projector.modelo.dto.DtoMovieProjector;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieprojector")
@Api(tags={"Controlador consulta movie projector"})
public class QueryControllerMovieProjector {

    HandlerListMovieProjector handlerListMovieProjector;
    HandlerListMovieProjectorByIdMovieProjector handlerListMovieProjectorByIdMovieProjector;
    HandlerListMovieProjectorById handlerListMovieProjectorById;
    @Autowired
    public QueryControllerMovieProjector(HandlerListMovieProjector handlerListMovieProjector,
                                         HandlerListMovieProjectorByIdMovieProjector handlerListMovieProjectorByIdMovieProjector,
                                         HandlerListMovieProjectorById handlerListMovieProjectorById){
        this.handlerListMovieProjector = handlerListMovieProjector;
        this.handlerListMovieProjectorByIdMovieProjector = handlerListMovieProjectorByIdMovieProjector;
        this.handlerListMovieProjectorById = handlerListMovieProjectorById;
    }

    @GetMapping
    @ApiOperation("Lista movie projector")
    public List<DtoMovieProjector> listarMovieProjector(){
        return this.handlerListMovieProjector.execute();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Lista movie projector")
    public List<DtoMovieProjector> movieProjectorsForId(@PathVariable Long id){
        return this.handlerListMovieProjectorByIdMovieProjector.execute(id);
    }

    @GetMapping(value = "/movie-projector-id/{id}")
    public DtoMovieProjector getMovieProjector(@PathVariable Long id){
        return this.handlerListMovieProjectorById.execute(id);
    }


}
