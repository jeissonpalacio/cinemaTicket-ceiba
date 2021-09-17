package com.ceiba.boleto.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.boleto.comando.ComandoBoleto;
import com.ceiba.boleto.comando.fabrica.FabricaBoleto;
import com.ceiba.boleto.modelo.entidad.Boleto;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.boleto.servicio.ServicioCrearBoleto;
import org.springframework.stereotype.Component;


@Component
public class ManejadorCrearBoleto implements ManejadorComandoRespuesta<ComandoBoleto, ComandoRespuesta<Long>> {

    private final FabricaBoleto fabricaBoleto;
    private final ServicioCrearBoleto servicioCrearBoleto;


    public ManejadorCrearBoleto(FabricaBoleto fabricaBoleto, ServicioCrearBoleto servicioCrearBoleto){
        this.fabricaBoleto = fabricaBoleto;
        this.servicioCrearBoleto = servicioCrearBoleto;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoBoleto comandoBoleto) {
        Boleto boleto = this.fabricaBoleto.crear(comandoBoleto);
        Long idTicket = this.servicioCrearBoleto.ejecutar(boleto);
        return new ComandoRespuesta<>(idTicket);
    }
}
