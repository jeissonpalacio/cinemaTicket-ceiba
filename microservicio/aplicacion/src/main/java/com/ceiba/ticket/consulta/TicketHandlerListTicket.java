package com.ceiba.ticket.consulta;


import com.ceiba.ticket.modelo.dto.DtoTicket;
import com.ceiba.ticket.puerto.dao.DaoTicket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketHandlerListTicket {


    private final DaoTicket daoTicket;

    public TicketHandlerListTicket(DaoTicket daoTicket) {
        this.daoTicket = daoTicket;
    }

    public List<DtoTicket> execute(){
        return this.daoTicket.listTicket();
    }

}
