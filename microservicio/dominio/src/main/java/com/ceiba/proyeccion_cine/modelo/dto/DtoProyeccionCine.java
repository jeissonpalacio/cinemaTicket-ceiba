package com.ceiba.proyeccion_cine.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class DtoProyeccionCine {

    Integer id;
    LocalDate movieProjection;
    LocalTime hourMovie;
    Integer idMovie;
    Integer idCinema;

}
