package com.ceiba.movie_projector.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.movie_projector.modelo.dto.DtoMovieProjector;
import com.ceiba.movie_projector.puerto.dao.DaoMovieProjector;
import com.ceiba.ticket.adaptador.repositorio.MapeoTicketModel;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoMovieProjectorMySql  implements DaoMovieProjector {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "movie_projector",value = "list")
    private static String sqlListarMovieProjector;

    @SqlStatement(namespace = "movie_projector",value = "listbyIdMovie")
    private static String sqlListMovieProjectorByIdMovie;


    public DaoMovieProjectorMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoMovieProjector> listMovieProjector() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarMovieProjector,new MapeoMovieProjector());
    }

    @Override
    public List<DtoMovieProjector> listMovieProjectorByIdMovie(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListMovieProjectorByIdMovie,parameterSource,new MapeoMovieProjector());
    }
}
