package com.ceiba.boleto.adaptador.repositorio;

import com.ceiba.boleto.modelo.entidad.Boleto;
import com.ceiba.boleto.puerto.repositorio.RepositorioBoleto;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryBoletoMySql implements RepositorioBoleto {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="boleto", value="crearboleto")
    private static String  sqlCrearBoleto;

    @SqlStatement(namespace="boleto", value="borrarboleto")
    private static String  sqlBorrarBoleto;

    @SqlStatement(namespace = "boleto",value = "validarexistenciaboleto")
    private static String sqlExistenciaBoleto;

    @SqlStatement(namespace = "boleto",value = "obtenerboleto")
    private static String sqlObtenerBoleto;

    @SqlStatement(namespace = "boleto",value = "actualizarboleto")
    private static String sqlActualizarBoleto;

    public RepositoryBoletoMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crearBoleto(Boleto boleto) {
        return this.customNamedParameterJdbcTemplate.crear(boleto,sqlCrearBoleto);
    }

    @Override
    public void eliminarBoleto(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlBorrarBoleto,paramSource);
    }

    @Override
    public boolean validarExistenciaBoleto(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistenciaBoleto,parameterSource,Boolean.class);
    }

    @Override
    public Boleto obtenerBoleto(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerBoleto,parameterSource,new MapperTicketModel());
    }

    @Override
    public void cambiarProyeccionBoleto(Boleto boleto) {
        this.customNamedParameterJdbcTemplate.actualizar(boleto,sqlActualizarBoleto);
    }


}
