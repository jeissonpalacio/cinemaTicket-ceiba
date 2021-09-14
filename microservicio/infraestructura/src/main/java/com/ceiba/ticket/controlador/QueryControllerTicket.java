package com.ceiba.ticket.controlador;

import com.ceiba.ticket.consulta.HandlerListByIdClient;
import com.ceiba.ticket.consulta.TicketHandlerListTicket;
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
public class QueryControllerTicket {

    private final TicketHandlerListTicket ticketHandlerListTicket;
    private final HandlerListByIdClient handlerListByIdClient;
    public QueryControllerTicket(TicketHandlerListTicket ticketHandlerListTicket,
                                 HandlerListByIdClient handlerListByIdClient
                                      ) {
        this.ticketHandlerListTicket = ticketHandlerListTicket;
        this.handlerListByIdClient = handlerListByIdClient;

    }

    @GetMapping
    @ApiOperation("Listar ticket")
    public List<DtoTicket> getTicket(){
        return this.ticketHandlerListTicket.execute();
    }

    @GetMapping(value = "/consultar-por-id-cliente/{id}")
    @ApiOperation("Obtener tickets por id client")
    public List<DtoTicket> getTicketForIdClient(@PathVariable Long id){
        return this.handlerListByIdClient.execute(id);

    }

}
