package com.ceiba.seats.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.movie_projector.adaptador.dao.MapeoMovieProjector;
import com.ceiba.seats.modelo.dto.DtoSeats;
import com.ceiba.seats.puerto.dao.DaoSeats;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoSeatsMysql implements DaoSeats {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="seats", value="list")
    private static String sqlListarSeats;

    @SqlStatement(namespace = "seats", value = "consultbyidmovieprojector")
    private static String sqlconsultByIdMovieProjector;

    public DaoSeatsMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoSeats> listSeats() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarSeats,new MapeoSeats());
    }

    @Override
    public List<DtoSeats> listSeatsByIdMovieProjector(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlconsultByIdMovieProjector,parameterSource,new MapeoSeats());
    }


}
