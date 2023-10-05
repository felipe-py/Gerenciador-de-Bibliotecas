package com.uefs.gerenciadorparabibliotecas.dao.reserva;

import com.uefs.gerenciadorparabibliotecas.dao.CRUD;
import com.uefs.gerenciadorparabibliotecas.exeptions.reservaExcetions.ReservaException;
import com.uefs.gerenciadorparabibliotecas.model.Reserva;

import java.util.List;
/**
 * Interface para o ReservaDAOList
 */
public interface ReservaDAO extends CRUD<Reserva, ReservaException> {
    List<Reserva> getReservas();
}
