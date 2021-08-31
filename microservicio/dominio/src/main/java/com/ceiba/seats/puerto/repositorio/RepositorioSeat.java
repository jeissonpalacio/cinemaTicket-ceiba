package com.ceiba.seats.puerto.repositorio;

import com.ceiba.seats.modelo.entidad.Seat;

public interface RepositorioSeat {

    void actualizarSeat(Integer idSeat,Long idTicket, Integer available);

    boolean validarSeat(Integer idSeat);

    Long consultavailable(Integer idSeat);

    void actualizarSeatAvailable(Long idTicket,Integer available);
}
