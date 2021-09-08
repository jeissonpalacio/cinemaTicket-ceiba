package com.ceiba.ticket.consulta;


import com.ceiba.ticket.modelo.dto.DtoTicket;
import com.ceiba.ticket.puerto.dao.DaoTicket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarTicket {


    private final DaoTicket daoTicket;

    public ManejadorListarTicket(DaoTicket daoTicket) {
        this.daoTicket = daoTicket;
    }

    public List<DtoTicket> ejecutar(){
        return this.daoTicket.listarTicket();
    }
}
