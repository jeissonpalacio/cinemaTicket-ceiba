package com.ceiba.asientos.consulta;

import com.ceiba.asientos.modelo.dto.DtoAsiento;
import com.ceiba.asientos.puerto.dao.DaoAsiento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarAsiento {

    private DaoAsiento daoAsiento;

    public ManejadorListarAsiento(DaoAsiento daoAsiento){
        this.daoAsiento = daoAsiento;

    }

    public List<DtoAsiento> ejecutar(){
        return this.daoAsiento.listarAsiento();
    }

}

