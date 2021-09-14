package com.ceiba.ticket.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.ticket.modelo.entidad.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperTicketModel implements RowMapper<Ticket>, MapperResult {


    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer idClient = rs.getInt("id_client");
        Double  amount = rs.getDouble("amount");
        Integer idMovieProjector = rs.getInt("id_movie_projector");

        return new Ticket(idClient,amount,idMovieProjector);
    }
}
