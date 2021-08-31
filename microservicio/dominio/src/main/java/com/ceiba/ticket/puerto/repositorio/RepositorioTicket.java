package com.ceiba.ticket.puerto.repositorio;

import com.ceiba.ticket.modelo.entidad.Ticket;

public interface RepositorioTicket {
    /**
     * Permite crear un ticket
     * @param ticket
     * @return el id generado
     */
    Long crearTicket(Ticket ticket);
    void eliminarTicket(Long id);
    boolean validarExiste(Long id);
    Ticket obtenerTicket(Long id);
    void cambiarProyeccionTicket(Ticket ticket);

}
