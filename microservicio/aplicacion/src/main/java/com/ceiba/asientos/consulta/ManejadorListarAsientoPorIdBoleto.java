package com.ceiba.asientos.consulta;

import com.ceiba.asientos.modelo.dto.DtoAsiento;
import com.ceiba.asientos.puerto.dao.DaoAsiento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarAsientoPorIdBoleto {
    private DaoAsiento daoAsiento;

    public ManejadorListarAsientoPorIdBoleto(DaoAsiento daoAsiento){
        this.daoAsiento = daoAsiento;
    }

    public List<DtoAsiento> ejecutar(Long id){
        return this.daoAsiento.ListarAsientoPorIdBoleto(id);
    }
}
