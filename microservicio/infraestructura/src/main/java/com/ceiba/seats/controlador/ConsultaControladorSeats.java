package com.ceiba.seats.controlador;

import com.ceiba.seats.consulta.ManejadorListarSeats;
import com.ceiba.seats.modelo.dto.DtoSeats;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seats")
@Api(tags = {"Controlador consulta seats"})
public class ConsultaControladorSeats {

    private final ManejadorListarSeats manejadorListarSeats;

    @Autowired
    public ConsultaControladorSeats(ManejadorListarSeats manejadorListarSeats) {
        this.manejadorListarSeats = manejadorListarSeats;
    }

    @GetMapping
    @ApiOperation("Listar seats")
    public List<DtoSeats> listarSets(){

        return this.manejadorListarSeats.ejecutarListarSeats();

    }


}
