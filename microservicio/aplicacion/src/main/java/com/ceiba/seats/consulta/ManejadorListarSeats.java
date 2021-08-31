package com.ceiba.seats.consulta;

import com.ceiba.seats.modelo.dto.DtoSeats;
import com.ceiba.seats.puerto.dao.DaoSeats;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarSeats {

    private DaoSeats daoSeats;

    public ManejadorListarSeats(DaoSeats daoSeats){
        this.daoSeats = daoSeats;

    }

    public List<DtoSeats> ejecutarListarSeats(){
        return this.daoSeats.listSeats();
    }
}

