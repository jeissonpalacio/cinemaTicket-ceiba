package com.ceiba.pelicula.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pelicula.modelo.dto.DtoPelicula;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MapperMovie implements RowMapper<DtoPelicula>, MapperResult {

    @Override
    public DtoPelicula mapRow(ResultSet rs, int rowNum) throws SQLException {

        int id = rs.getInt("id_movie");
        String name = rs.getString("name_movie");
        String type = rs.getString("type_movie");
        String lenght = rs.getString("lenght");
        int rating = rs.getInt("rating");

        return new DtoPelicula(id,name,type,lenght,rating);
    }
}
