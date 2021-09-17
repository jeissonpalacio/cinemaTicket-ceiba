package com.ceiba.asientos.controlador;

import com.ceiba.asientos.consulta.ManejadorListarAsientoPorId;
import com.ceiba.asientos.consulta.ManejadorListarAsientoPorIdBoleto;
import com.ceiba.asientos.consulta.ManejadorListarAsiento;
import com.ceiba.asientos.modelo.dto.DtoAsiento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@Api(tags = {"Controlador consulta seats"})
public class ConsultaControladorAsiento {

    private final ManejadorListarAsiento manejadorListarAsiento;
    private final ManejadorListarAsientoPorIdBoleto manejadorListarAsientoPorIdBoleto;
    private final ManejadorListarAsientoPorId manejadorListarAsientoPorId;
    @Autowired
    public ConsultaControladorAsiento(ManejadorListarAsiento manejadorListarAsiento,
                                      ManejadorListarAsientoPorIdBoleto manejadorListarAsientoPorIdBoleto,
                                      ManejadorListarAsientoPorId manejadorListarAsientoPorId) {
        this.manejadorListarAsiento = manejadorListarAsiento;
        this.manejadorListarAsientoPorIdBoleto = manejadorListarAsientoPorIdBoleto;
        this.manejadorListarAsientoPorId = manejadorListarAsientoPorId;
    }

    @GetMapping
    @ApiOperation("Listar seats")
    public List<DtoAsiento> listarAsiento(){

        return this.manejadorListarAsiento.ejecutar();

    }
    @GetMapping(value = "/{id}")
    @ApiOperation("Consultar asiento por id")
    public List<DtoAsiento> consultarAsientoPorId(@PathVariable Long id){
        return this.manejadorListarAsientoPorId.ejecutar(id);
    }

    @GetMapping(value = "/consult-by-id-ticket/{id}")
    @ApiOperation("Consultar por id del ticket")
    public List<DtoAsiento> consultarPorIdDelBoleto(@PathVariable Long id){

        return manejadorListarAsientoPorIdBoleto.ejecutar(id);

    }


}
