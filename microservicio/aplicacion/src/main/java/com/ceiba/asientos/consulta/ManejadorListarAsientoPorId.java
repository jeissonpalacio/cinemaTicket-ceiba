package com.ceiba.asientos.consulta;

import com.ceiba.asientos.modelo.dto.DtoAsiento;
import com.ceiba.asientos.puerto.dao.DaoAsiento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarAsientoPorId {

    private DaoAsiento daoAsiento;

    public ManejadorListarAsientoPorId(DaoAsiento daoAsiento){
        this.daoAsiento = daoAsiento;

    }
    public List<DtoAsiento> ejecutar(Long id){
        return  this.daoAsiento.ListarAsientoPorIdProyeccionPelicula(id);
    }

}
