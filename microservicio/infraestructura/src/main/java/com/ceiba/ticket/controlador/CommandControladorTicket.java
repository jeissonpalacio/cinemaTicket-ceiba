package com.ceiba.ticket.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.ticket.comando.CommandTicket;
import com.ceiba.ticket.comando.manejador.HandlerUpdateTicket;
import com.ceiba.ticket.comando.manejador.HandlerCreateTicket;
import com.ceiba.ticket.comando.manejador.HandlerDeleteTicket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@Api(tags = { "Controlador comando ticket"})
public class CommandControladorTicket {
    private final HandlerCreateTicket handlerCreateTicket;
    private final HandlerDeleteTicket handlerDeleteTicket;
    private final HandlerUpdateTicket handlerUpdateTicket;

    @Autowired
    public CommandControladorTicket(HandlerCreateTicket handlerCreateTicket,
                                    HandlerDeleteTicket handlerDeleteTicket,
                                    HandlerUpdateTicket handlerUpdateTicket
    ){
        this.handlerCreateTicket = handlerCreateTicket;
        this.handlerDeleteTicket = handlerDeleteTicket;
        this.handlerUpdateTicket = handlerUpdateTicket;
    }
    @PostMapping
    @ApiOperation("Crear ticket")
    public ComandoRespuesta<Long> crearTicket(@RequestBody CommandTicket commandTicket){
        return this.handlerCreateTicket.ejecutar(commandTicket);
    }

    @DeleteMapping(value="/{id}")
    @ApiOperation("Eliminar ticket")
    public void eliminarTicket(@PathVariable Long id){

        this.handlerDeleteTicket.ejecutar(id);
    }

    @PutMapping(value="/{id}")
    @ApiOperation("Actualizar ticket")
    public void actualizar(@RequestBody CommandTicket commandTicket, @PathVariable Long id){
        commandTicket.setIdTicket(id);
        handlerUpdateTicket.ejecutar(commandTicket);

    }


}
