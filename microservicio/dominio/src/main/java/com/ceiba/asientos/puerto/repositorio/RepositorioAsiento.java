package com.ceiba.asientos.puerto.repositorio;


public interface RepositorioAsiento {

    void actualizarAsiento(Integer idSeat, Long idTicket, Integer available);

    boolean validarAsiento(Integer idSeat);

    Long consultarDisponibilidad(Integer idSeat);

    void actualizarDisponibilidadAsiento(Long idTicket, Long newidTicket, Integer available);
}
