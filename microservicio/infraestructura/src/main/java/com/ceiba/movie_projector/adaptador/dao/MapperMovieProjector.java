package com.ceiba.movie_projector.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.movie_projector.modelo.dto.DtoMovieProjector;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MapperMovieProjector implements RowMapper<DtoMovieProjector>, MapperResult {


    @Override
    public DtoMovieProjector mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer idMovieProjector = rs.getInt("id_movie_projector");
        LocalDate dateMovieProjector = rs.getObject(2, LocalDate.class);
        LocalTime hourMovieProjector = rs.getObject(3,LocalTime.class);
        Integer idMovie = rs.getInt("id_movie");
        Integer idCinema = rs.getInt("id_cinema");
        return new DtoMovieProjector(idMovieProjector,dateMovieProjector,hourMovieProjector,idMovie,idCinema);
    }
}
