package com.ceiba.cinema.consulta;

import com.ceiba.cinema.modelo.dto.DtoCinema;
import com.ceiba.cinema.puerto.dao.DaoCinema;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandlerListCinema {

    DaoCinema daoCinema;

    public HandlerListCinema(DaoCinema daoCinema){
        this.daoCinema = daoCinema;
    }

    public List<DtoCinema> executeListCinema(){
        return this.daoCinema.listCinema();
    }
}
