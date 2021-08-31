package com.ceiba.movie_projector.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class DtoMovieProjector {

    Integer id;
    LocalDate movieProjection;
    LocalTime hourMovie;
    Integer idMovie;
    Integer idCinema;

}
