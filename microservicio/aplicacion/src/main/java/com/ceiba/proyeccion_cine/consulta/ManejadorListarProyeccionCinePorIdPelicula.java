package com.ceiba.proyeccion_cine.consulta;

import com.ceiba.proyeccion_cine.modelo.dto.DtoProyeccionCine;
import com.ceiba.proyeccion_cine.puerto.dao.DaoProyeccionCine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarProyeccionCinePorIdPelicula {

    private DaoProyeccionCine daoProyeccionCine;

    public ManejadorListarProyeccionCinePorIdPelicula(DaoProyeccionCine daoProyeccionCine){
        this.daoProyeccionCine = daoProyeccionCine;
    }

    public List<DtoProyeccionCine> ejecutar(Long id){
        return this.daoProyeccionCine.listarProyeccionCinePorIdPelicula(id);
    }
}
