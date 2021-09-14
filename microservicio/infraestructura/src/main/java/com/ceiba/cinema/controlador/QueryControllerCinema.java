package com.ceiba.cinema.controlador;

import com.ceiba.cinema.consulta.HandlerListCinema;
import com.ceiba.cinema.modelo.dto.DtoCinema;
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
public class QueryControllerCinema {

    HandlerListCinema handlerListCinema;

    @Autowired
    public QueryControllerCinema(HandlerListCinema handlerListCinema){
        this.handlerListCinema = handlerListCinema;
    }

    @GetMapping
    @ApiOperation("Lista movie projector")
    public List<DtoCinema> getCinema(){
        return this.handlerListCinema.executeListCinema();
    }
}
