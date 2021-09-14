package com.ceiba.ticket.puerto.repositorio;

import com.ceiba.ticket.modelo.entidad.Ticket;

public interface RepositorioTicket {
    /**
     * Permite crear un ticket
     * @param ticket
     * @return el id generado
     */
    Long createTicket(Ticket ticket);
    void deleteTicket(Long id);
    boolean validateExiste(Long id);
    Ticket getTicket(Long id);
    void changeProjectionTicket(Ticket ticket);

}
