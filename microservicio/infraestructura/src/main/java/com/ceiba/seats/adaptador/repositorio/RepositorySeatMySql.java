package com.ceiba.seats.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.seats.modelo.entidad.Seat;
import com.ceiba.seats.puerto.repositorio.RepositorioSeat;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorySeatMySql implements RepositorioSeat {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorySeatMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace="seats", value="updateseats")
    private static String sqlUpdateSeat;


    @SqlStatement(namespace = "seats",value = "existseat")
    private static String sqlExistSeats;

    @SqlStatement(namespace = "seats",value = "consultavailable")
    private static String sqlConsultAvailable;

    @SqlStatement(namespace = "seats",value = "updateSeatAvailable")
    private static String sqlSeatUpdateAvailable;

    @Override
    public void upgradeSeat(Integer idSeat, Long idTicket, Integer available) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idSeat",idSeat);
        paramSource.addValue("idTicket", idTicket);
        paramSource.addValue("available",available);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlUpdateSeat, paramSource);
    }

    @Override
    public boolean validateSeat(Integer idSeat) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idSeat", idSeat);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistSeats,paramSource, Boolean.class);

    }

    @Override
    public Long consultavailable(Integer idSeat) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idSeat", idSeat);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultAvailable,paramSource,Long.class);
    }

    @Override
    public void upgradeSeatAvailable(Long idTicket, Long newidTicket, Integer available) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("IdTicketNew",newidTicket);
        paramSource.addValue("idTicket", idTicket);
        paramSource.addValue("available",available);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlSeatUpdateAvailable, paramSource);
    }


}
