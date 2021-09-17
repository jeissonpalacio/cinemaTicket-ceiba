package com.ceiba.pelicula.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pelicula.modelo.dto.DtoPelicula;
import com.ceiba.pelicula.puerto.dao.DaoPelicula;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoPeliculaMysql implements DaoPelicula {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "pelicula",value = "listarpelicula")
    private static String sqlList;

    public DaoPeliculaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }


    @Override
    public List<DtoPelicula> listarPelicula() {
       return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlList,new MapperMovie() );
    }
}
