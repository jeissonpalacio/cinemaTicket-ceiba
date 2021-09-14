package com.ceiba.ticket.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.seats.excepcion.ExcepcionQuantity;
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
    private static final String ERROR_CANTIDAD_INCORRECTA = "error de cantidad";

    public static final int TAMANO_MINIMO_SILLAS = 0;
    public static final int TAMANO_MAXIMO_SILLAS = 2;
    public static final int LIMITE_PRECIO_ERRONEO = 0;


    Long idTicket;
    Integer idClient;
    Double amount;
    Integer idMovieProjector;
    List<Integer> idSeats;

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
        validatePrice(amount);
        validarCantidad(idSeats);
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
        validatePrice(amount);
        validarCantidad(idSeats);
        validarObligatorio(idClient,SE_DEBE_INGRESAR_ID_DEL_CLIENT);
        validarObligatorio(amount,SE_DEBE_TENER_UN_MONTO);
        ValidadorArgumento.validarPositivo(amount,SE_DEBE_TENER_UN_MONTO_REAL);
        validarObligatorio(idMovieProjector,SE_DEBE_TENER_UN_ID_DE_LA_PROYECCION);
        this.idTicket = idTicket;
        this.idClient = idClient;
        this.amount = amount;
        this.idMovieProjector = idMovieProjector;
        this.idSeats = idSeats;
    }
    
    private void validatePrice(double price){
        if(price<= LIMITE_PRECIO_ERRONEO){
            throw new ExcepcionQuantity(ERROR_CANTIDAD_INCORRECTA);
        }
    }

    private void validarCantidad(List<Integer> listId){
        boolean existe = listId.size()> TAMANO_MINIMO_SILLAS && listId.size()<= TAMANO_MAXIMO_SILLAS;
        if(!existe){
            throw new ExcepcionQuantity(SOLO_PUEDE_TENER_DOS_ASIENTOS);
        }
    }



}
