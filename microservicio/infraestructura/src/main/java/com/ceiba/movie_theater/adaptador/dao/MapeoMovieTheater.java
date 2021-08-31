package com.ceiba.movie_theater.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.movie_theater.modelo.dto.DtoMovieTheater;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MapeoMovieTheater implements RowMapper<DtoMovieTheater>, MapperResult {


    @Override
    public DtoMovieTheater mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer idMovieTheater = rs.getInt("id_movie_theater");
        Integer numberTheater = rs.getInt("number_theater");
        Integer numberSeats = rs.getInt("number_seats");


        return new DtoMovieTheater(idMovieTheater,numberTheater,numberSeats);
    }
}
