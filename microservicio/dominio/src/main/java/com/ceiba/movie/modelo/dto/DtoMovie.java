package com.ceiba.movie.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoMovie {

    private Integer id;
    private String name;
    private String typeMovie;
    private String lenght;
    private Integer rating;

}
