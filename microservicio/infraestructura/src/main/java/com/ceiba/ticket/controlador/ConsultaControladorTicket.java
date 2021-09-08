package com.ceiba.ticket.controlador;

import com.ceiba.ticket.consulta.ManejadorListarTicket;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@Api(tags={"Controlador consulta usuario"})
public class ConsultaControladorTicket {

    private final ManejadorListarTicket manejadorListarTicket;

    public ConsultaControladorTicket(ManejadorListarTicket manejadorListarTicket) {
        this.manejadorListarTicket = manejadorListarTicket;
    }

    @GetMapping
    @ApiOperation("Listar ticket")
    public List<DtoTicket> getTicket(){
        return this.manejadorListarTicket.ejecutar();
    }
}
