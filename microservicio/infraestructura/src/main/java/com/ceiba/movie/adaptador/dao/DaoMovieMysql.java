package com.ceiba.movie.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.movie.modelo.dto.DtoMovie;
import com.ceiba.movie.puerto.dao.DaoMovie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoMovieMysql  implements DaoMovie {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "movie",value = "list")
    private static String sqlList;

    public DaoMovieMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }


    @Override
    public List<DtoMovie> ListMovie() {
       return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlList,new MapperMovie() );
    }
}
