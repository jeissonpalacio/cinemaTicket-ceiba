package com.ceiba.boleto.comando.fabrica;

import com.ceiba.boleto.comando.ComandoBoleto;
import com.ceiba.boleto.modelo.entidad.Boleto;
import org.springframework.stereotype.Component;

@Component
public class FabricaBoleto {

    public Boleto crear(ComandoBoleto comandoBoleto){

        if(comandoBoleto.getIdTicket()!=null){
            return new Boleto(comandoBoleto.getIdTicket(), comandoBoleto.getIdClient(),
                    comandoBoleto.getAmount(), comandoBoleto.getIdMovieProjector(), comandoBoleto.getIdSeats());

        }
         return new Boleto(comandoBoleto.getIdClient(), comandoBoleto.getAmount(),
                 comandoBoleto.getIdMovieProjector(), comandoBoleto.getIdSeats());
    }
}
