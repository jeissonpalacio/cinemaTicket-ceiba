package com.ceiba.movie_projector.consulta;

import com.ceiba.movie_projector.modelo.dto.DtoMovieProjector;
import com.ceiba.movie_projector.puerto.dao.DaoMovieProjector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarMovieProjector {

    DaoMovieProjector daoMovieProjector;

    public ManejadorListarMovieProjector(DaoMovieProjector daoMovieProjector){
        this.daoMovieProjector = daoMovieProjector;
    }

    public List<DtoMovieProjector> ejecutar(){
        return this.daoMovieProjector.listMovieProjector();
    }
}
