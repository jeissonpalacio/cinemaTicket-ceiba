package com.ceiba.boleto.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoBoleto {

    private Long idTicket;
    private Integer idClient;
    private Double amount;
    private Integer idMovieProjector;
    private List<Integer> idSeats;
}
