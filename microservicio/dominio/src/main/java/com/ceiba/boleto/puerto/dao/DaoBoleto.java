package com.ceiba.boleto.puerto.dao;

import com.ceiba.boleto.modelo.dto.DtoBoleto;

import java.util.List;

public interface DaoBoleto {

    List<DtoBoleto> listarBoleto();
    List<DtoBoleto> obtenerBoletoPorIdClient(Long id);
}
