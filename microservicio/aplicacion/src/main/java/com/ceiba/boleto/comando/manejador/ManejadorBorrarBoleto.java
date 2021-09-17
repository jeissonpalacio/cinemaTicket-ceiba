package com.ceiba.boleto.comando.manejador;

import com.ceiba.boleto.servicio.ServicioBorrarBoleto;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorBorrarBoleto implements ManejadorComando<Long> {

    private final ServicioBorrarBoleto servicioBorrarBoleto;

    public ManejadorBorrarBoleto(ServicioBorrarBoleto servicioBorrarBoleto
    ){
        this.servicioBorrarBoleto = servicioBorrarBoleto;
    }

    @Override
    public void ejecutar(Long idTicket) {
        this.servicioBorrarBoleto.ejecutar(idTicket);
    }
}
