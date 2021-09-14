package com.ceiba.seats.puerto.repositorio;


public interface RepositorioSeat {

    void upgradeSeat(Integer idSeat, Long idTicket, Integer available);

    boolean validateSeat(Integer idSeat);

    Long consultavailable(Integer idSeat);

    void upgradeSeatAvailable(Long idTicket, Long newidTicket, Integer available);
}
