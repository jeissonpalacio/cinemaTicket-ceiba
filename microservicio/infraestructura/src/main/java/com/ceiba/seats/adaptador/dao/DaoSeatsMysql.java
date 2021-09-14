package com.ceiba.seats.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.seats.modelo.dto.DtoSeats;
import com.ceiba.seats.puerto.dao.DaoSeats;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoSeatsMysql implements DaoSeats {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="seats", value="list")
    private static String sqlListSeats;

    @SqlStatement(namespace = "seats", value = "consultbyidmovieprojector")
    private static String sqlConsultByIdMovieProjector;


    @SqlStatement(namespace = "seats",value = "getseatbyidticket")
    private static String sqlGetSeatByIdTicket;

    public DaoSeatsMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoSeats> listSeats() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListSeats,new MapperSeats());
    }

    @Override
    public List<DtoSeats> listSeatsByIdMovieProjector(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlConsultByIdMovieProjector,parameterSource,new MapperSeats());
    }

    @Override
    public List<DtoSeats> listSeatsByIdTicket(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlGetSeatByIdTicket,parameterSource,new MapperSeats());
    }


}
