package com.ceiba.asientos.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.asientos.puerto.repositorio.RepositorioAsiento;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryAsientoMySql implements RepositorioAsiento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositoryAsientoMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace="asiento", value="actualizarasiento")
    private static String sqlActualizarasiento;


    @SqlStatement(namespace = "asiento",value = "existenciaasiento")
    private static String sqlExistenciaAsiento;

    @SqlStatement(namespace = "asiento",value = "consultardisponible")
    private static String  sqlConsultarDisponibile;

    @SqlStatement(namespace = "asiento",value = "actualizarasientodisponibilidad")
    private static String sqlActualizarAsientoDisponible;

    @Override
    public void actualizarAsiento(Integer idSeat, Long idTicket, Integer available) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idSeat",idSeat);
        paramSource.addValue("idTicket", idTicket);
        paramSource.addValue("available",available);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarasiento, paramSource);
    }

    @Override
    public boolean validarAsiento(Integer idSeat) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idSeat", idSeat);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistenciaAsiento,paramSource, Boolean.class);

    }

    @Override
    public Long consultarDisponibilidad(Integer idSeat) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idSeat", idSeat);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultarDisponibile,paramSource,Long.class);
    }

    @Override
    public void actualizarDisponibilidadAsiento(Long idTicket, Long newidTicket, Integer available) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("IdTicketNew",newidTicket);
        paramSource.addValue("idTicket", idTicket);
        paramSource.addValue("available",available);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarAsientoDisponible, paramSource);
    }


}
