package com.ceiba.seats.adaptador.dao;


import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.seats.modelo.dto.DtoSeats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoSeats implements RowMapper<DtoSeats>, MapperResult {
    @Override
    public DtoSeats mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer id = rs.getInt("id_seats");
        String numberSeat = rs.getString("number_seat");
        Integer available = rs.getInt("available");


        return new DtoSeats(id,numberSeat,available);
    }
}
