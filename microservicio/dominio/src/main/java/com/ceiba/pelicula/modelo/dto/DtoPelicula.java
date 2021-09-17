package com.ceiba.pelicula.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoPelicula {

    private Integer id;
    private String name;
    private String typeMovie;
    private String lenght;
    private Integer rating;

}
