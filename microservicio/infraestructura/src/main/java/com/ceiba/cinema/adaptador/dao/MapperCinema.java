package com.ceiba.cinema.adaptador.dao;

import com.ceiba.cinema.modelo.dto.DtoCinema;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperCinema implements RowMapper<DtoCinema>, MapperResult {


    @Override
    public DtoCinema mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer idCinema = rs.getInt("id_cinema");
        String nameCinema = rs.getString("name_cinema");
        String place = rs.getString("place");
        Double priceFunction = rs.getDouble("price_function");

        return new DtoCinema(idCinema,nameCinema,place,priceFunction);
    }
}
