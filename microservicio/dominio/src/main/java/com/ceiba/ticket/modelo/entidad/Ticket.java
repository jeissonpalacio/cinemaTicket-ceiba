package com.ceiba.ticket.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Ticket {
    private static final String SE_DEBE_INGRESAR_ID_DEL_CLIENT = "Se debe ingresar el id del client";
    private static final String SE_DEBE_TENER_UN_MONTO_REAL = "Se debe tener un monto real";
    private static final String SE_DEBE_TENER_UN_MONTO = "se debe tener un valor para la compra";
    private static final String SE_DEBE_TENER_UN_ID_DE_LA_PROYECCION = "Se debe tener un id de la proyeccion";
    private static final int LONGITUD_MINIMA_AMOUNT = 8;


    Long idTicket;
    Integer idClient;
    Double amount;
    Integer idMovieProjector;

    public Ticket(Integer idClient,Double amount,Integer idMovieProjector){
        validarObligatorio(idClient,SE_DEBE_INGRESAR_ID_DEL_CLIENT);
        validarObligatorio(amount,SE_DEBE_TENER_UN_MONTO);
        ValidadorArgumento.validarPositivo(amount,SE_DEBE_TENER_UN_MONTO_REAL);
        validarObligatorio(idMovieProjector,SE_DEBE_TENER_UN_ID_DE_LA_PROYECCION);

        this.idClient = idClient;
        this.amount = amount;
        this.idMovieProjector = idMovieProjector;
    }
    public Ticket(Long idTicket,Integer idClient,Double amount,Integer idMovieProjector){
        validarObligatorio(idClient,SE_DEBE_INGRESAR_ID_DEL_CLIENT);
        validarObligatorio(amount,SE_DEBE_TENER_UN_MONTO);
        ValidadorArgumento.validarPositivo(amount,SE_DEBE_TENER_UN_MONTO_REAL);
        validarObligatorio(idMovieProjector,SE_DEBE_TENER_UN_ID_DE_LA_PROYECCION);

        this.idClient = idClient;
        this.amount = amount;
        this.idMovieProjector = idMovieProjector;
    }

}
