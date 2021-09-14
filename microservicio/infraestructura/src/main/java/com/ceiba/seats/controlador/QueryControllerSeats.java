package com.ceiba.seats.controlador;

import com.ceiba.seats.consulta.HandlerListSeatById;
import com.ceiba.seats.consulta.HandlerListSeatByIdTicket;
import com.ceiba.seats.consulta.HandlerListSeats;
import com.ceiba.seats.modelo.dto.DtoSeats;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@Api(tags = {"Controlador consulta seats"})
public class QueryControllerSeats {

    private final HandlerListSeats handlerListSeats;
    private final HandlerListSeatByIdTicket handlerListSeatByIdTicket;
    private final HandlerListSeatById handlerListSeatById;
    @Autowired
    public QueryControllerSeats(HandlerListSeats handlerListSeats,
                                HandlerListSeatByIdTicket handlerListSeatByIdTicket,
                                HandlerListSeatById handlerListSeatById) {
        this.handlerListSeats = handlerListSeats;
        this.handlerListSeatByIdTicket = handlerListSeatByIdTicket;
        this.handlerListSeatById = handlerListSeatById;
    }

    @GetMapping
    @ApiOperation("Listar seats")
    public List<DtoSeats> listarSets(){

        return this.handlerListSeats.execute();

    }
    @GetMapping(value = "/{id}")
    @ApiOperation("Lista seats")
    public List<DtoSeats> consultseatbyid(@PathVariable Long id){
        return this.handlerListSeatById.execute(id);
    }

    @GetMapping(value = "/consult-by-id-ticket/{id}")
    @ApiOperation("Consultar por id del ticket")
    public List<DtoSeats> consultarByIdTicket(@PathVariable Long id){

        return handlerListSeatByIdTicket.execute(id);

    }


}
