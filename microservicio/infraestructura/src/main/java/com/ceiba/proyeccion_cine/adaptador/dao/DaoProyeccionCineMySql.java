package com.ceiba.proyeccion_cine.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.proyeccion_cine.modelo.dto.DtoProyeccionCine;
import com.ceiba.proyeccion_cine.puerto.dao.DaoProyeccionCine;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoProyeccionCineMySql implements DaoProyeccionCine {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "proyeccion_cine",value = "listarproyeccioncine")
    private static String  sqlListarProyeccionCine;

    @SqlStatement(namespace = "proyeccion_cine",value = "listarporidpelicula")
    private static String  sqlListarProyeccionCinePorIdPelicula;

    @SqlStatement(namespace = "proyeccion_cine",value = "consultarproyeccioncine")
    private static String sqlObtenerConsultarProyeccionCine;
    public DaoProyeccionCineMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoProyeccionCine> listarProyeccionCine() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarProyeccionCine,new MapperMovieProjector());
    }

    @Override
    public List<DtoProyeccionCine> listarProyeccionCinePorIdPelicula(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarProyeccionCinePorIdPelicula,parameterSource,new MapperMovieProjector());
    }
    @Override
    public DtoProyeccionCine listarProyeccionCinePorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);

        return  this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerConsultarProyeccionCine,parameterSource,new MapperMovieProjector());
    }


}
