package com.ceiba.proyeccion_cine.consulta;

import com.ceiba.proyeccion_cine.modelo.dto.DtoProyeccionCine;
import com.ceiba.proyeccion_cine.puerto.dao.DaoProyeccionCine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarProyeccionCine {

    DaoProyeccionCine daoProyeccionCine;

    public ManejadorListarProyeccionCine(DaoProyeccionCine daoProyeccionCine){
        this.daoProyeccionCine = daoProyeccionCine;
    }

    public List<DtoProyeccionCine> ejecutar(){
        return this.daoProyeccionCine.listarProyeccionCine();
    }
}
