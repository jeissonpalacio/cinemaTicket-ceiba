package com.ceiba.boleto.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.boleto.modelo.dto.DtoBoleto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperTicket implements RowMapper<DtoBoleto>, MapperResult {
    @Override
    public DtoBoleto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id_ticket");
        Long idClient = rs.getLong("id_client");
        Double amount = rs.getDouble("amount");
        Long idMovieProjector = rs.getLong("id_movie_projector");


        return new DtoBoleto(id,idClient,amount,idMovieProjector);
    }
}
