package com.uefs.gerenciadorparabibliotecas.dao.reserva;

import com.uefs.gerenciadorparabibliotecas.dao.CRUD;
import com.uefs.gerenciadorparabibliotecas.exeptions.reservaExcetions.ReservaException;
import com.uefs.gerenciadorparabibliotecas.model.Reserva;

import java.util.List;

public interface ReservaDAO extends CRUD<Reserva, ReservaException> {
    List<Reserva> getReservas();
}
