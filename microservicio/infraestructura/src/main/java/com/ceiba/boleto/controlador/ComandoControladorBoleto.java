package com.ceiba.boleto.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.boleto.comando.ComandoBoleto;
import com.ceiba.boleto.comando.manejador.ManejadorActualizarBoleto;
import com.ceiba.boleto.comando.manejador.ManejadorBorrarBoleto;
import com.ceiba.boleto.comando.manejador.ManejadorCrearBoleto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@Api(tags = { "Controlador comando ticket"})
public class ComandoControladorBoleto {
    private final ManejadorCrearBoleto manejadorCrearBoleto;
    private final ManejadorBorrarBoleto manejadorBorrarBoleto;
    private final ManejadorActualizarBoleto manejadorActualizarBoleto;

    @Autowired
    public ComandoControladorBoleto(ManejadorCrearBoleto manejadorCrearBoleto,
                                    ManejadorBorrarBoleto manejadorBorrarBoleto,
                                    ManejadorActualizarBoleto manejadorActualizarBoleto
    ){
        this.manejadorCrearBoleto = manejadorCrearBoleto;
        this.manejadorBorrarBoleto = manejadorBorrarBoleto;
        this.manejadorActualizarBoleto = manejadorActualizarBoleto;
    }
    @PostMapping
    @ApiOperation("Crear ticket")
    public ComandoRespuesta<Long> crearTicket(@RequestBody ComandoBoleto comandoBoleto){
        return this.manejadorCrearBoleto.ejecutar(comandoBoleto);
    }

    @DeleteMapping(value="/{id}")
    @ApiOperation("Eliminar ticket")
    public void eliminarTicket(@PathVariable Long id){

        this.manejadorBorrarBoleto.ejecutar(id);
    }

    @PutMapping(value="/{id}")
    @ApiOperation("Actualizar ticket")
    public void actualizar(@RequestBody ComandoBoleto comandoBoleto, @PathVariable Long id){
        comandoBoleto.setIdTicket(id);
        manejadorActualizarBoleto.ejecutar(comandoBoleto);

    }


}
