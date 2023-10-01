package com.uefs.gerenciadorparabibliotecas.exeptions.reservaExcetions;
/**
 * GERENCIADOR DE BIBLIOTECA
 * @author Luis Felipe Cunha Silva
 * @author Lucas Lima Rodrigues
 * @version 1.0
 */
import com.uefs.gerenciadorparabibliotecas.model.Reserva;

/**
 * Classe que reúne todas as exceções possíveis da reserva
 * deletar, atualizar ou buscar reserva
 */
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
