package com.ceiba.cine.consulta;

import com.ceiba.cine.modelo.dto.DtoCinema;
import com.ceiba.cine.puerto.dao.DaoCinema;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarCine {

    DaoCinema daoCinema;

    public ManejadorListarCine(DaoCinema daoCinema){
        this.daoCinema = daoCinema;
    }

    public List<DtoCinema> executeListCinema(){
        return this.daoCinema.listarCine();
    }
}
