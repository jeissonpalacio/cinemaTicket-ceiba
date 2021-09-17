package com.ceiba.pelicula.consulta;

import com.ceiba.pelicula.modelo.dto.DtoPelicula;
import com.ceiba.pelicula.puerto.dao.DaoPelicula;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarPeliculas {

    private final DaoPelicula daoPelicula;

    public ManejadorListarPeliculas(DaoPelicula daoPelicula){
        this.daoPelicula = daoPelicula;
    }

    public List<DtoPelicula> ejecutar(){
        return this.daoPelicula.listarPelicula();
    }
}
