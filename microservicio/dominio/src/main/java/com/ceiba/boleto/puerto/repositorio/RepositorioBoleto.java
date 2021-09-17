package com.ceiba.boleto.puerto.repositorio;

import com.ceiba.boleto.modelo.entidad.Boleto;

public interface RepositorioBoleto {
    /**
     * Permite crear un ticket
     * @param boleto
     * @return el id generado
     */
    Long crearBoleto(Boleto boleto);
    void eliminarBoleto(Long id);
    boolean validarExistenciaBoleto(Long id);
    Boleto obtenerBoleto(Long id);
    void cambiarProyeccionBoleto(Boleto boleto);

}
