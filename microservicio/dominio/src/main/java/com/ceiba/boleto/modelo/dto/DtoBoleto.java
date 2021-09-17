package com.ceiba.boleto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoBoleto {
    Long id;
    Long idClient;
    Double amount;
    Long idMovieProjector;


}
