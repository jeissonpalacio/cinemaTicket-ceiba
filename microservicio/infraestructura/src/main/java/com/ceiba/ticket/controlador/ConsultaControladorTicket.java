package com.ceiba.ticket.controlador;

import com.ceiba.ticket.consulta.ManejadorListarPorIdDelClient;
import com.ceiba.ticket.consulta.ManejadorListarTicket;
import com.ceiba.ticket.modelo.dto.DtoTicket;
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
public class ConsultaControladorTicket {

    private final ManejadorListarTicket manejadorListarTicket;
    private final ManejadorListarPorIdDelClient manejadorListarPorIdDelClient;
    public ConsultaControladorTicket(ManejadorListarTicket manejadorListarTicket,
                                     ManejadorListarPorIdDelClient manejadorListarPorIdDelClient
                                      ) {
        this.manejadorListarTicket = manejadorListarTicket;
        this.manejadorListarPorIdDelClient = manejadorListarPorIdDelClient;

    }

    @GetMapping
    @ApiOperation("Listar ticket")
    public List<DtoTicket> getTicket(){
        return this.manejadorListarTicket.ejecutar();
    }

    @GetMapping(value = "/consultar-por-id-cliente/{id}")
    @ApiOperation("Obtener tickets por id client")
    public List<DtoTicket> getTicketForIdClient(@PathVariable Long id){
        System.out.println(this.manejadorListarPorIdDelClient.ejecutar(id));
        return this.manejadorListarPorIdDelClient.ejecutar(id);

    }

}
