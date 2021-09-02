package com.ceiba.ticket.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.seats.excepcion.ExcepcionCantidad;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
@Setter
public class Ticket {
    private static final String SE_DEBE_INGRESAR_ID_DEL_CLIENT = "Se debe ingresar el id del client";
    private static final String SE_DEBE_TENER_UN_MONTO_REAL = "Se debe tener un monto real";
    private static final String SE_DEBE_TENER_UN_MONTO = "se debe tener un valor para la compra";
    private static final String SE_DEBE_TENER_UN_ID_DE_LA_PROYECCION = "Se debe tener un id de la proyeccion";
    private static final int LONGITUD_MINIMA_AMOUNT = 8;
    private static final String SOLO_PUEDE_TENER_DOS_ASIENTOS = "Solo puede tener dos asientos";


    Long idTicket;
    Integer idClient;
    Double amount;
    Integer idMovieProjector;
    List<Integer> idSeats;

    // El modelo solo debe tener atributos del modelo en la base de datos o puede tener otros atributos necesarios
    // para una logica de negocio

    public Ticket(Integer idClient,Double amount,Integer idMovieProjector){
        validarObligatorio(idClient,SE_DEBE_INGRESAR_ID_DEL_CLIENT);
        validarObligatorio(amount,SE_DEBE_TENER_UN_MONTO);
        ValidadorArgumento.validarPositivo(amount,SE_DEBE_TENER_UN_MONTO_REAL);
        validarObligatorio(idMovieProjector,SE_DEBE_TENER_UN_ID_DE_LA_PROYECCION);
        this.idClient = idClient;
        this.amount = amount;
        this.idMovieProjector = idMovieProjector;
    }
    public Ticket(Integer idClient,Double amount,Integer idMovieProjector,List<Integer> idSeats){
        validarObligatorio(idClient,SE_DEBE_INGRESAR_ID_DEL_CLIENT);
        validarObligatorio(amount,SE_DEBE_TENER_UN_MONTO);
        ValidadorArgumento.validarPositivo(amount,SE_DEBE_TENER_UN_MONTO_REAL);
        validarObligatorio(idMovieProjector,SE_DEBE_TENER_UN_ID_DE_LA_PROYECCION);
        this.idClient = idClient;
        this.amount = amount;
        this.idMovieProjector = idMovieProjector;
        this.idSeats = idSeats;
    }

    public Ticket(Long idTicket, Integer idClient, Double amount, Integer idMovieProjector,List<Integer> idSeats) {
        validarObligatorio(idClient,SE_DEBE_INGRESAR_ID_DEL_CLIENT);
        validarObligatorio(amount,SE_DEBE_TENER_UN_MONTO);
        ValidadorArgumento.validarPositivo(amount,SE_DEBE_TENER_UN_MONTO_REAL);
        validarObligatorio(idMovieProjector,SE_DEBE_TENER_UN_ID_DE_LA_PROYECCION);
        validarCantidad(idSeats);
        this.idTicket = idTicket;
        this.idClient = idClient;
        this.amount = amount;
        this.idMovieProjector = idMovieProjector;
        this.idSeats = idSeats;
    }

    public void validarCantidad(List<Integer> listId){
        boolean existe = listId.size()>0 && listId.size()<=2;
        if(!existe){
            throw new ExcepcionCantidad(SOLO_PUEDE_TENER_DOS_ASIENTOS);
        }
    };

}
