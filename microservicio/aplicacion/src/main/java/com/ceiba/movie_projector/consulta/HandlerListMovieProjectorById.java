package com.ceiba.movie_projector.consulta;

import com.ceiba.movie_projector.modelo.dto.DtoMovieProjector;
import com.ceiba.movie_projector.puerto.dao.DaoMovieProjector;
import org.springframework.stereotype.Component;

@Component
public class HandlerListMovieProjectorById {

    private DaoMovieProjector daoMovieProjector;

    public HandlerListMovieProjectorById(DaoMovieProjector daoMovieProjector){
            this.daoMovieProjector = daoMovieProjector;
    }

    public DtoMovieProjector execute(Long id){
        return daoMovieProjector.listMovieProjectoById(id);
    }

}
