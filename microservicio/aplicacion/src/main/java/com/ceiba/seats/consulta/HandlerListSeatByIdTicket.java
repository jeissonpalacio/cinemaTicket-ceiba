package com.ceiba.seats.consulta;

import com.ceiba.seats.modelo.dto.DtoSeats;
import com.ceiba.seats.puerto.dao.DaoSeats;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandlerListSeatByIdTicket {
    private DaoSeats daoSeats;

    public HandlerListSeatByIdTicket(DaoSeats daoSeats){
        this.daoSeats = daoSeats;
    }

    public List<DtoSeats> execute(Long id){
        return this.daoSeats.listSeatsByIdTicket(id);
    }
}
