package com.ceiba.asientos.adaptador.dao;

import com.ceiba.asientos.puerto.dao.DaoAsiento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.asientos.modelo.dto.DtoAsiento;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoAsientoMysql implements DaoAsiento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="asiento", value="listar")
    private static String sqlListarAsientos;

    @SqlStatement(namespace = "asiento", value = "consultarporidproyeccioncine")
    private static String sqlConsultarPorIdProyeccionCine;


    @SqlStatement(namespace = "asiento",value = "obtenerporidboleto")
    private static String sqlObtenerPorIdBoleto;

    public DaoAsientoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoAsiento> listarAsiento() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarAsientos,new MapperSeats());
    }

    @Override
    public List<DtoAsiento> ListarAsientoPorIdProyeccionPelicula(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlConsultarPorIdProyeccionCine,parameterSource,new MapperSeats());
    }

    @Override
    public List<DtoAsiento> ListarAsientoPorIdBoleto(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlObtenerPorIdBoleto,parameterSource,new MapperSeats());
    }


}
