package com.ceiba.ticket.consulta;

import com.ceiba.ticket.modelo.dto.DtoTicket;
import com.ceiba.ticket.puerto.dao.DaoTicket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarPorIdDelClient {
    private final DaoTicket daoTicket;

    public ManejadorListarPorIdDelClient(DaoTicket daoTicket){
        this.daoTicket = daoTicket;
    }
    public List<DtoTicket> ejecutar(Long id){
        return  this.daoTicket.getTicketForIdClient(id);
    }
}
