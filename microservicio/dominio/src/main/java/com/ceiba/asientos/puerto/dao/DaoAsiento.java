package com.ceiba.asientos.puerto.dao;

import com.ceiba.asientos.modelo.dto.DtoAsiento;

import java.util.List;

public interface DaoAsiento {

    List<DtoAsiento> listarAsiento();
    List<DtoAsiento> ListarAsientoPorIdProyeccionPelicula(Long id);
    List<DtoAsiento> ListarAsientoPorIdBoleto(Long id);
}
