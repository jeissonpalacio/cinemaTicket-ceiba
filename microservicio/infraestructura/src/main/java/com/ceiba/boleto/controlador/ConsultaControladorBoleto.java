package com.ceiba.boleto.controlador;

import com.ceiba.boleto.consulta.ManejadorListarPorIdCliente;
import com.ceiba.boleto.consulta.ManejadorListarBoleto;
import com.ceiba.boleto.modelo.dto.DtoBoleto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@Api(tags={"Controlador consulta usuario"})
public class ConsultaControladorBoleto {

    private final ManejadorListarBoleto manejadorListarBoleto;
    private final ManejadorListarPorIdCliente manejadorListarPorIdCliente;
    public ConsultaControladorBoleto(ManejadorListarBoleto manejadorListarBoleto,
                                     ManejadorListarPorIdCliente manejadorListarPorIdCliente
                                      ) {
        this.manejadorListarBoleto = manejadorListarBoleto;
        this.manejadorListarPorIdCliente = manejadorListarPorIdCliente;

    }

    @GetMapping
    @ApiOperation("Listar ticket")
    public List<DtoBoleto> getTicket(){
        return this.manejadorListarBoleto.ejecutar();
    }

    @GetMapping(value = "/consultar-por-id-cliente/{id}")
    @ApiOperation("Obtener tickets por id client")
    public List<DtoBoleto> getTicketForIdClient(@PathVariable Long id){
        return this.manejadorListarPorIdCliente.ejecutar(id);

    }

}
