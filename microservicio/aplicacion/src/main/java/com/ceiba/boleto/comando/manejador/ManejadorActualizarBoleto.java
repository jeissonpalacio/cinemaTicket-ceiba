package com.ceiba.boleto.comando.manejador;

import com.ceiba.boleto.servicio.ServicioActualizarBoleto;
import com.ceiba.manejador.ManejadorComando;
import com.ceiba.boleto.comando.ComandoBoleto;
import com.ceiba.boleto.comando.fabrica.FabricaBoleto;
import com.ceiba.boleto.modelo.entidad.Boleto;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarBoleto implements ManejadorComando<ComandoBoleto> {
    private final FabricaBoleto fabricaBoleto;
    private final ServicioActualizarBoleto servicioActualizarBoleto;


    public ManejadorActualizarBoleto(FabricaBoleto fabricaBoleto,
                                     ServicioActualizarBoleto servicioActualizarBoleto) {
        this.fabricaBoleto = fabricaBoleto;
        this.servicioActualizarBoleto = servicioActualizarBoleto;
    }

    @Override
    public void ejecutar(ComandoBoleto comandoBoleto) {
        Boleto boleto = fabricaBoleto.crear(comandoBoleto);
        servicioActualizarBoleto.actualizarTicket(boleto);
    }
}
