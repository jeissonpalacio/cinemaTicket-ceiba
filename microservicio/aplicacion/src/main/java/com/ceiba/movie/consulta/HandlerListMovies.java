package com.ceiba.movie.consulta;

import com.ceiba.movie.modelo.dto.DtoMovie;
import com.ceiba.movie.puerto.dao.DaoMovie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandlerListMovies {

    private final DaoMovie daoMovie;

    public HandlerListMovies(DaoMovie daoMovie){
        this.daoMovie = daoMovie;
    }

    public List<DtoMovie> executeList(){
        return this.daoMovie.ListMovie();
    }
}
