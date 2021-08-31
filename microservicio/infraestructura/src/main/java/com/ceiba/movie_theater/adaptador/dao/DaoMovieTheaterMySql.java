package com.ceiba.movie_theater.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.movie_theater.modelo.dto.DtoMovieTheater;
import com.ceiba.movie_theater.puerto.dao.DaoMovieTheater;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoMovieTheaterMySql  implements DaoMovieTheater {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="movie_theater", value="list")
    private static String sqlListarMovieTheater;

    public DaoMovieTheaterMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }


    @Override
    public List<DtoMovieTheater> listMovieTheater() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarMovieTheater,new MapeoMovieTheater());
    }
}
