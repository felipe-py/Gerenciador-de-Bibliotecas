package com.uefs.gerenciadorparabibliotecas.dao.reserva;

import com.uefs.gerenciadorparabibliotecas.dao.CRUD;
import com.uefs.gerenciadorparabibliotecas.model.Reserva;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ReservaDAO extends CRUD<Reserva> {
    List<Reserva> getReservas();
}