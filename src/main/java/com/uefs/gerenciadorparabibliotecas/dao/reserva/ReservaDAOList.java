package com.uefs.gerenciadorparabibliotecas.dao.reserva;

import com.uefs.gerenciadorparabibliotecas.exeptions.reservaExcetions.ReservaException;
import com.uefs.gerenciadorparabibliotecas.model.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaDAOList implements ReservaDAO{

    List<Reserva> reservas = new ArrayList<>();
    private Integer novoID = 0;

    public ReservaDAOList() {
        this.reservas = reservas;
        this.novoID = novoID;
    }

    @Override
    public Reserva criar(Reserva reserva) {
        reserva.setReservaID(this.novoID);
        this.novoID++;
        this.reservas.add(reserva);
        reserva.getLivroReservado().setStatusReserva(true);
        return reserva;
    }

    @Override
    public void deletar(Reserva reserva) throws ReservaException {
        boolean remove = this.reservas.remove(reserva);
        if (!remove) {
            throw new ReservaException(ReservaException.DELETE, reserva);
        }
    }

    @Override
    public void resetar() {
        this.reservas = new ArrayList<>();
        this.novoID = 0;
    }

    @Override
    public Reserva procurarPorID(Integer id) throws ReservaException{
        for (Reserva reserva : this.reservas) {
            if (reserva.getReservaID() == id) {
                return reserva;
            }
        }
        throw new ReservaException(ReservaException.SEARCH, id);
    }

    @Override
    public List<Reserva> getReservas() {
        return reservas;
    }
}
