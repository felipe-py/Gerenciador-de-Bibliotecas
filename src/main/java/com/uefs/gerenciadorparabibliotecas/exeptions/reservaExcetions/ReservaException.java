package com.uefs.gerenciadorparabibliotecas.exeptions.reservaExcetions;

import com.uefs.gerenciadorparabibliotecas.model.Reserva;

public class ReservaException extends Exception{
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";
    public static final String SEARCH = "Operação de BUSCA não realizada.";
    private Reserva reserva;

    public ReservaException(String create, Reserva reserva) {
        super(create);
        this.reserva = reserva;
    }

    public ReservaException(String create, Integer id) {
        super(create + "ID inválido:" + id);
        this.reserva = reserva;
    }
}
