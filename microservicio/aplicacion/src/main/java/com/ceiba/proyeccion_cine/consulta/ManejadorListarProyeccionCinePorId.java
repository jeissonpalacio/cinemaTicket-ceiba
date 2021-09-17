package com.ceiba.proyeccion_cine.consulta;

import com.ceiba.proyeccion_cine.modelo.dto.DtoProyeccionCine;
import com.ceiba.proyeccion_cine.puerto.dao.DaoProyeccionCine;
import org.springframework.stereotype.Component;

@Component
public class ManejadorListarProyeccionCinePorId {

    private DaoProyeccionCine daoProyeccionCine;

    public ManejadorListarProyeccionCinePorId(DaoProyeccionCine daoProyeccionCine){
            this.daoProyeccionCine = daoProyeccionCine;
    }

    public DtoProyeccionCine ejecutar(Long id){
        return daoProyeccionCine.listarProyeccionCinePorId(id);
    }

}
