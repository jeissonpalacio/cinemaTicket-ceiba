package com.ceiba.cine.adaptador.dao;

import com.ceiba.cine.modelo.dto.DtoCinema;
import com.ceiba.cine.puerto.dao.DaoCinema;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoCineMySql implements  DaoCinema{

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "cine",value = "listarcine")
    private static String sqlListCinema;

    public DaoCineMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoCinema> listarCine() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListCinema,new MapperCine());
    }
}
