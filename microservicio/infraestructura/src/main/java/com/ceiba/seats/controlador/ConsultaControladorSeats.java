package com.ceiba.seats.controlador;

import com.ceiba.seats.consulta.ManejadorListarSeatPorIdTicket;
import com.ceiba.seats.consulta.ManejadorListarSeats;
import com.ceiba.seats.modelo.dto.DtoSeats;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@Api(tags = {"Controlador consulta seats"})
public class ConsultaControladorSeats {

    private final ManejadorListarSeats manejadorListarSeats;
    private final ManejadorListarSeatPorIdTicket manejadorListarSeatPorIdTicket;
    @Autowired
    public ConsultaControladorSeats(ManejadorListarSeats manejadorListarSeats,
                                    ManejadorListarSeatPorIdTicket manejadorListarSeatPorIdTicket) {
        this.manejadorListarSeats = manejadorListarSeats;
        this.manejadorListarSeatPorIdTicket = manejadorListarSeatPorIdTicket;
    }

    @GetMapping
    @ApiOperation("Listar seats")
    public List<DtoSeats> listarSets(){

        return this.manejadorListarSeats.ejecutarListarSeats();

    }
    @GetMapping(value = "/{id}")
    @ApiOperation("Lista seats")
    public List<DtoSeats> consultseatbyid(@PathVariable Long id){
        return this.manejadorListarSeats.listarSeatById(id);
    }

    @GetMapping(value = "/consult-by-id-ticket/{id}")
    @ApiOperation("Consultar por id del ticket")
    public List<DtoSeats> consultarByIdTicket(@PathVariable Long id){

        return manejadorListarSeatPorIdTicket.ejecutar(id);

    }


}
