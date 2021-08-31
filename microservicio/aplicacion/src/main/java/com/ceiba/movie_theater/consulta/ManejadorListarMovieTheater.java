package com.ceiba.movie_theater.consulta;

import com.ceiba.movie_theater.modelo.dto.DtoMovieTheater;
import com.ceiba.movie_theater.puerto.dao.DaoMovieTheater;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarMovieTheater {

    private DaoMovieTheater daoMovieTheater;

    public ManejadorListarMovieTheater(DaoMovieTheater daoMovieTheater){
        this.daoMovieTheater = daoMovieTheater;
    }

    public List<DtoMovieTheater> ejecutarListarMovieTheater(){
        return this.daoMovieTheater.listMovieTheater();
    }

}
