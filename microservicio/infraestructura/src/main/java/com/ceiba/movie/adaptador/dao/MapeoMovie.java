package com.ceiba.movie.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.movie.modelo.dto.DtoMovie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MapeoMovie implements RowMapper<DtoMovie>, MapperResult {

    @Override
    public DtoMovie mapRow(ResultSet rs, int rowNum) throws SQLException {

        int id = rs.getInt("id_movie");
        String name = rs.getString("name_movie");
        String type = rs.getString("type_movie");
        String lenght = rs.getString("lenght");
        int rating = rs.getInt("rating");

        return new DtoMovie(id,name,type,lenght,rating);
    }
}
