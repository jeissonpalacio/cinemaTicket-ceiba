package com.ceiba.ticket.consulta;

import com.ceiba.seats.modelo.dto.DtoSeats;
import com.ceiba.seats.puerto.dao.DaoSeats;

import java.util.List;

public class ManejadorListarTicket {

    private final DaoSeats daoSeats;

    public ManejadorListarTicket(DaoSeats daoSeats) {
        this.daoSeats = daoSeats;
    }

    public List<DtoSeats> listarSeats(){
        return this.daoSeats.listSeats();
    }
}
