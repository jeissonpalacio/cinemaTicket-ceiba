package com.ceiba.ticket.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.ticket.comando.ComandoTicket;
import com.ceiba.ticket.comando.manejador.ManejadorActualizarTicket;
import com.ceiba.ticket.comando.manejador.ManejadorCrearTicket;
import com.ceiba.ticket.comando.manejador.ManejadorEliminarTicket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@Api(tags = { "Controlador comando ticket"})
public class ComandoControladorTicket {
    private final ManejadorCrearTicket manejadorCrearTicket;
    private final ManejadorEliminarTicket manejadorEliminarTicket;
    private final ManejadorActualizarTicket manejadorActualizarTicket;

    @Autowired
    public ComandoControladorTicket(ManejadorCrearTicket manejadorCrearTicket,
                                    ManejadorEliminarTicket manejadorEliminarTicket,
                                    ManejadorActualizarTicket manejadorActualizarTicket
    ){
        this.manejadorCrearTicket = manejadorCrearTicket;
        this.manejadorEliminarTicket = manejadorEliminarTicket;
        this.manejadorActualizarTicket = manejadorActualizarTicket;
    }
    @PostMapping
    @ApiOperation("Crear ticket")
    public ComandoRespuesta<Long> crearTicket(@RequestBody ComandoTicket comandoTicket){
        return this.manejadorCrearTicket.ejecutar(comandoTicket);
    }

    @DeleteMapping(value="/{id}")
    @ApiOperation("Eliminar ticket")
    public void eliminarTicket(@PathVariable Long id){

        this.manejadorEliminarTicket.ejecutar(id);
    }

    @PutMapping(value="/{id}")
    @ApiOperation("Actualizar ticket")
    public void actualizar(@RequestBody ComandoTicket comandoTicket,@PathVariable Long id){
        comandoTicket.setIdTicket(id);
        manejadorActualizarTicket.ejecutar(comandoTicket);

    }


}
