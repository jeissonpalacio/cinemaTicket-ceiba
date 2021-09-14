package com.ceiba.seats.puerto.dao;

import com.ceiba.seats.modelo.dto.DtoSeats;

import java.util.List;

public interface DaoSeats {

    List<DtoSeats> listSeats();
    List<DtoSeats> listSeatsByIdMovieProjector(Long id);
    List<DtoSeats> listSeatsByIdTicket(Long id);
}
