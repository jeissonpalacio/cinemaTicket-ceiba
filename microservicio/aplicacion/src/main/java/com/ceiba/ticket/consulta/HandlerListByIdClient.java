package com.ceiba.ticket.consulta;

import com.ceiba.ticket.modelo.dto.DtoTicket;
import com.ceiba.ticket.puerto.dao.DaoTicket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandlerListByIdClient {
    private final DaoTicket daoTicket;

    public HandlerListByIdClient(DaoTicket daoTicket){
        this.daoTicket = daoTicket;
    }

    public List<DtoTicket> execute(Long id){
        return  this.daoTicket.getTicketForIdClient(id);
    }
}
