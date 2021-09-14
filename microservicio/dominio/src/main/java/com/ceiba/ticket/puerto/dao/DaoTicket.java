package com.ceiba.ticket.puerto.dao;

import com.ceiba.ticket.modelo.dto.DtoTicket;

import java.util.List;

public interface DaoTicket {

    List<DtoTicket> listTicket();
    List<DtoTicket> getTicketForIdClient(Long id);
}
