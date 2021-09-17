package com.ceiba.boleto.consulta;


import com.ceiba.boleto.modelo.dto.DtoBoleto;
import com.ceiba.boleto.puerto.dao.DaoBoleto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarBoleto {


    private final DaoBoleto daoBoleto;

    public ManejadorListarBoleto(DaoBoleto daoBoleto) {
        this.daoBoleto = daoBoleto;
    }

    public List<DtoBoleto> ejecutar(){
        return this.daoBoleto.listarBoleto();
    }

}
