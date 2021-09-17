package com.ceiba.boleto.adaptador.dao;

import com.ceiba.boleto.puerto.dao.DaoBoleto;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.boleto.modelo.dto.DtoBoleto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoBoletoMysql implements DaoBoleto {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;


    @SqlStatement(namespace="boleto", value="listarboleto")
    private static String sqlListarBoleto;

    @SqlStatement(namespace = "boleto",value = "obtenerboletoporidcliente")
    private static String sqlObtenerBoletoPorIdCliente;

    public DaoBoletoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoBoleto> listarBoleto() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarBoleto, new MapperTicket());
    }

    @Override
    public List<DtoBoleto> obtenerBoletoPorIdClient(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlObtenerBoletoPorIdCliente,parameterSource,new MapperTicket());
    }
}
