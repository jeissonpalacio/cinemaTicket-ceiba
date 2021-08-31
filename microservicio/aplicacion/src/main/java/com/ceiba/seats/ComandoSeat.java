package com.ceiba.seats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSeat {

    Integer idSeat;
    String numberSeat;
    Integer available;
    Integer idTicket;

}
