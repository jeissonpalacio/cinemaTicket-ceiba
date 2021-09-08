package com.ceiba.ticket.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import com.ceiba.ticket.puerto.dao.DaoTicket;
import com.ceiba.usuario.adaptador.dao.MapeoUsuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoTicketMysql implements DaoTicket {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;


    @SqlStatement(namespace="ticket", value="listarTicket")
    private static String sqlListarTicket;

    public DaoTicketMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoTicket> listarTicket() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarTicket, new MapeoTicket());
    }
}
