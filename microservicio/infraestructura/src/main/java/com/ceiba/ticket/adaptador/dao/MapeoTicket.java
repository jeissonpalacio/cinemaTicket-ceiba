package com.ceiba.ticket.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoTicket implements RowMapper<DtoTicket>, MapperResult {
    @Override
    public DtoTicket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id_ticket");
        Long idClient = rs.getLong("id_client");
        Double amount = rs.getDouble("amount");
        Long idMovieProjector = rs.getLong("id_movie_projector");


        return new DtoTicket(id,idClient,amount,idMovieProjector);
    }
}
