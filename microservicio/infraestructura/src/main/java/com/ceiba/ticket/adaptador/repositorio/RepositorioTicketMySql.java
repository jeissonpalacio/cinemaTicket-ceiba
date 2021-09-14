package com.ceiba.ticket.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioTicketMySql implements RepositorioTicket {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="ticket", value="createTicket")
    private static String sqlCreateTicket;

    @SqlStatement(namespace="ticket", value="deleteTicket")
    private static String sqlEliminar;

    @SqlStatement(namespace = "ticket",value = "validateExistTicket")
    private static String sqlExiste;

    @SqlStatement(namespace = "ticket",value = "getTicket")
    private static String sqlGetTicket;

    @SqlStatement(namespace = "ticket",value = "updateTicket")
    private static String sqlUpdate;

    public RepositorioTicketMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long createTicket(Ticket ticket) {
        return this.customNamedParameterJdbcTemplate.crear(ticket,sqlCreateTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar,paramSource);
    }

    @Override
    public boolean validateExiste(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,parameterSource,Boolean.class);
    }

    @Override
    public Ticket getTicket(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlGetTicket,parameterSource,new MapeoTicketModel());
    }

    @Override
    public void changeProjectionTicket(Ticket ticket) {
        this.customNamedParameterJdbcTemplate.actualizar(ticket,sqlUpdate);
    }


}
