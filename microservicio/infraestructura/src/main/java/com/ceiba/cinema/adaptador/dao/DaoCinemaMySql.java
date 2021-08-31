package com.ceiba.cinema.adaptador.dao;

import com.ceiba.cinema.modelo.dto.DtoCinema;
import com.ceiba.cinema.puerto.dao.DaoCinema;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoCinemaMySql implements  DaoCinema{

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "cinema",value = "list")
    private static String sqlListarCinema;

    public DaoCinemaMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoCinema> listCinema() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarCinema,new MapeoCinema());
    }
}
