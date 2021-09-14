package com.ceiba.movie_projector.consulta;

import com.ceiba.movie_projector.modelo.dto.DtoMovieProjector;
import com.ceiba.movie_projector.puerto.dao.DaoMovieProjector;
import org.springframework.stereotype.Component;

@Component
public class ManejadorListarMovieProjectorPorId {

    private DaoMovieProjector daoMovieProjector;

    public ManejadorListarMovieProjectorPorId(DaoMovieProjector daoMovieProjector){
            this.daoMovieProjector = daoMovieProjector;
    }

    public DtoMovieProjector ejecutar(Long id){
        return daoMovieProjector.listMovieProjectoById(id);
    }

}
