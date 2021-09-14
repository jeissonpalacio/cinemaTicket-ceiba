package com.ceiba.movie_projector.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.movie_projector.modelo.dto.DtoMovieProjector;
import com.ceiba.movie_projector.puerto.dao.DaoMovieProjector;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoMovieProjectorMySql  implements DaoMovieProjector {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "movie_projector",value = "list")
    private static String sqlListMovieProjector;

    @SqlStatement(namespace = "movie_projector",value = "listbyIdMovie")
    private static String sqlListMovieProjectorByIdMovie;

    @SqlStatement(namespace = "movie_projector",value = "consult_movie_projector")
    private static String getSqlconsultListMovieProjector;
    public DaoMovieProjectorMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoMovieProjector> listMovieProjector() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListMovieProjector,new MapperMovieProjector());
    }

    @Override
    public List<DtoMovieProjector> listMovieProjectorByIdMovie(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListMovieProjectorByIdMovie,parameterSource,new MapperMovieProjector());
    }
    @Override
    public DtoMovieProjector listMovieProjectoById(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);

        return  this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(getSqlconsultListMovieProjector,parameterSource,new MapperMovieProjector());
    }


}
