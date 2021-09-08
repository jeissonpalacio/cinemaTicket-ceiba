package com.ceiba.ticket.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoTicket {
    Long id;
    Long idClient;
    Double amount;
    Long idMovieProjector;


}
