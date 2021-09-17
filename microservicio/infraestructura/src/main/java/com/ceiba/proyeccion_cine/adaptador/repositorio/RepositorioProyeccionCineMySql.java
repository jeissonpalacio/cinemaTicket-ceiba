package com.ceiba.proyeccion_cine.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.proyeccion_cine.modelo.entidad.ProyeccionCine;
import com.ceiba.proyeccion_cine.puerto.repositorio.RepositorioProyeccionCine;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioProyeccionCineMySql implements RepositorioProyeccionCine {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;


    public RepositorioProyeccionCineMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace="proyeccion_cine", value="consultarproyeccioncine")
    private static String sqlFindById;

    @Override
    public ProyeccionCine buscarProyeccionCinePorId(Integer idMovieProjector) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idMovieProjector);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlFindById,paramSource, new MapperMovieProjectorModel());
    }
}
