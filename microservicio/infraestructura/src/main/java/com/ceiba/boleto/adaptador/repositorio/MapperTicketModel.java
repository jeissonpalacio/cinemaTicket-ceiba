package com.ceiba.boleto.adaptador.repositorio;

import com.ceiba.boleto.modelo.entidad.Boleto;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperTicketModel implements RowMapper<Boleto>, MapperResult {


    @Override
    public Boleto mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer idClient = rs.getInt("id_client");
        Double  amount = rs.getDouble("amount");
        Integer idMovieProjector = rs.getInt("id_movie_projector");

        return new Boleto(idClient,amount,idMovieProjector);
    }
}
