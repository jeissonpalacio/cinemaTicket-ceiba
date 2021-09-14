package com.ceiba.movie_projector.consulta;

import com.ceiba.movie_projector.modelo.dto.DtoMovieProjector;
import com.ceiba.movie_projector.puerto.dao.DaoMovieProjector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarMovieProjectorPorIdMovie {

    private DaoMovieProjector daoMovieProjector;

    public ManejadorListarMovieProjectorPorIdMovie(DaoMovieProjector daoMovieProjector){
        this.daoMovieProjector = daoMovieProjector;
    }

    public List<DtoMovieProjector> ejecutar(Long id){
        return this.daoMovieProjector.listMovieProjectorByIdMovie(id);
    }
}
