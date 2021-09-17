package com.ceiba.asientos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoAsiento {

    Integer idSeat;
    String numberSeat;
    Integer available;
    Integer idTicket;

}
