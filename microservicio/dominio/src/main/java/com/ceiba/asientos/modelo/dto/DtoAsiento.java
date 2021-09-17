package com.ceiba.asientos.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoAsiento {

    Integer id;
    String numberSeats;
    Integer available;

}
