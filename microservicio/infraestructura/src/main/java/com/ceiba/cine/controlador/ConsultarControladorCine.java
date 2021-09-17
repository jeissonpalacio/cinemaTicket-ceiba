package com.ceiba.cine.controlador;

import com.ceiba.cine.consulta.ManejadorListarCine;
import com.ceiba.cine.modelo.dto.DtoCinema;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinema")
@Api(tags = {"Controlador consulta cinema"})
public class ConsultarControladorCine {

    ManejadorListarCine manejadorListarCine;

    @Autowired
    public ConsultarControladorCine(ManejadorListarCine manejadorListarCine){
        this.manejadorListarCine = manejadorListarCine;
    }

    @GetMapping
    @ApiOperation("Lista movie projector")
    public List<DtoCinema> obtenerCine(){
        return this.manejadorListarCine.executeListCinema();
    }
}
