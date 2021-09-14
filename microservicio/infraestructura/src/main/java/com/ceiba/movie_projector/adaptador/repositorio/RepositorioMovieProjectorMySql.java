package com.ceiba.movie_projector.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import com.ceiba.movie_projector.puerto.repositorio.MovieProjectorRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioMovieProjectorMySql implements MovieProjectorRepository {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;


    public RepositorioMovieProjectorMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace="movie_projector", value="consult_movie_projector")
    private static String sqlFindById;

    @Override
    public MovieProjector findbyMovieProjectorForId(Integer idMovieProjector) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idMovieProjector);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlFindById,paramSource, new MapeoMovieProjectorModel());
    }
}
