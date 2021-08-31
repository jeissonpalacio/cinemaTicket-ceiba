package com.ceiba.movie_projector.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.movie_projector.modelo.entidad.MovieProjector;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MapeoMovieProjectorModel implements RowMapper<MovieProjector>, MapperResult {
    @Override
    public MovieProjector mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer idMovieProjector = rs.getInt("id_movie_projector");
        LocalDate dateMovieProjector = rs.getObject(2, LocalDate.class);
        LocalTime hourMovieProjector = rs.getObject(3,LocalTime.class);
        Integer idMovie = rs.getInt("id_movie");
        Integer idCinema = rs.getInt("id_cinema");


        return new MovieProjector(idMovieProjector,dateMovieProjector,hourMovieProjector,idMovie,idCinema);
    }
}
