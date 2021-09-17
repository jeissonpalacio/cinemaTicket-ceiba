package com.ceiba.proyeccion_cine.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.proyeccion_cine.modelo.dto.DtoProyeccionCine;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MapperMovieProjector implements RowMapper<DtoProyeccionCine>, MapperResult {


    @Override
    public DtoProyeccionCine mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer idMovieProjector = rs.getInt("id_movie_projector");
        LocalDate dateMovieProjector = rs.getObject(2, LocalDate.class);
        LocalTime hourMovieProjector = rs.getObject(3,LocalTime.class);
        Integer idMovie = rs.getInt("id_movie");
        Integer idCinema = rs.getInt("id_cinema");
        return new DtoProyeccionCine(idMovieProjector,dateMovieProjector,hourMovieProjector,idMovie,idCinema);
    }
}
