package com.ceiba.cinema.consulta;

import com.ceiba.cinema.modelo.dto.DtoCinema;
import com.ceiba.cinema.puerto.dao.DaoCinema;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListCinema {

    DaoCinema daoCinema;

    public ManejadorListCinema(DaoCinema daoCinema){
        this.daoCinema = daoCinema;
    }

    public List<DtoCinema> ejecutarListarCinema(){
        return this.daoCinema.listCinema();
    }
}
