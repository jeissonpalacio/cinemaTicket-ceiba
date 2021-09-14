package com.ceiba.ticket.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryTicketMySql implements RepositorioTicket {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="ticket", value="createTicket")
    private static String sqlCreateTicket;

    @SqlStatement(namespace="ticket", value="deleteTicket")
    private static String sqlDelete;

    @SqlStatement(namespace = "ticket",value = "validateExistTicket")
    private static String sqlExistTicket;

    @SqlStatement(namespace = "ticket",value = "getTicket")
    private static String sqlGetTicket;

    @SqlStatement(namespace = "ticket",value = "updateTicket")
    private static String sqlUpdate;

    public RepositoryTicketMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
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
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlDelete,paramSource);
    }

    @Override
    public boolean validateExiste(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistTicket,parameterSource,Boolean.class);
    }

    @Override
    public Ticket getTicket(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlGetTicket,parameterSource,new MapperTicketModel());
    }

    @Override
    public void changeProjectionTicket(Ticket ticket) {
        this.customNamedParameterJdbcTemplate.actualizar(ticket,sqlUpdate);
    }


}
