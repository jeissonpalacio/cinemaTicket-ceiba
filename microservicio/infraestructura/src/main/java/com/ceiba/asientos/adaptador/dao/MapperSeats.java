package com.ceiba.asientos.adaptador.dao;


import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.asientos.modelo.dto.DtoAsiento;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSeats implements RowMapper<DtoAsiento>, MapperResult {
    @Override
    public DtoAsiento mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer id = rs.getInt("id_seats");
        String numberSeat = rs.getString("number_seat");
        Integer available = rs.getInt("available");


        return new DtoAsiento(id,numberSeat,available);
    }
}
