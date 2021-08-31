package com.ceiba.movie.consulta;

import com.ceiba.movie.modelo.dto.DtoMovie;
import com.ceiba.movie.puerto.dao.DaoMovie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarMovies {

    private final DaoMovie daoMovie;

    public ManejadorListarMovies(DaoMovie daoMovie){
        this.daoMovie = daoMovie;
    }

    public List<DtoMovie> ejecutarListar(){
        return this.daoMovie.ListMovie();
    }
}
