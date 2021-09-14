package com.ceiba.movie_projector.consulta;

import com.ceiba.movie_projector.modelo.dto.DtoMovieProjector;
import com.ceiba.movie_projector.puerto.dao.DaoMovieProjector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandlerListMovieProjectorByIdMovieProjector {

    private DaoMovieProjector daoMovieProjector;

    public HandlerListMovieProjectorByIdMovieProjector(DaoMovieProjector daoMovieProjector){
        this.daoMovieProjector = daoMovieProjector;
    }

    public List<DtoMovieProjector> execute(Long id){
        return this.daoMovieProjector.listMovieProjectorByIdMovie(id);
    }
}
