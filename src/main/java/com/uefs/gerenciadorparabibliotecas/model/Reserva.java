package com.uefs.gerenciadorparabibliotecas.model;

import java.io.Serializable;
import java.util.Objects;
/**
 * Classe Model da reserva
 */
public class Reserva implements Serializable {
    /**
     * reservaID: inteiro que armazena indentificação da reserva
     * livroReservado: objeto livro que foi reservado
     * leitorReservador: objeto leitor que realizou a reserva
     */
    private Integer reservaID;
    private Livro livroReservado;

    private Leitor leitorReservador;

    /**
     * Acessa o ID da reserva
     * @return valor do ID da reserva
     */
    public Integer getReservaID() {
        return reservaID;
    }

    /**
     * Modifica o ID da reserva
     * @param reservaID novo ID para a reserva
     */
    public void setReservaID(Integer reservaID) {
        this.reservaID = reservaID;
    }

    /**
     * Acessa o livro que foi reservado
     * @return livro reservado
     */
    public Livro getLivroReservado() {
        return livroReservado;
    }

    /**
     * Modifica o livro que foi reservado
     * @param livroReservado novo livro reservado
     */
    public void setLivroReservado(Livro livroReservado) {
        this.livroReservado = livroReservado;
    }

    /**
     * Acessa o leitor que realizou a reserva
     * @return leiotr reservador
     */
    public Leitor getLeitorReservador() {
        return leitorReservador;
    }

    /**
     * Modifica o leitor que realizou a reserva
     * @param leitorReservador novo leitor
     */
    public void setLeitorReservador(Leitor leitorReservador) {
        this.leitorReservador = leitorReservador;
    }

    /**
     * Contrutor da reserva para usuário reservador e livro
     * @param leitorReservador leitor que realizou a reserva
     * @param livroReservado livro que foi reservado
     */
    public Reserva(Leitor leitorReservador, Livro livroReservado) {
        this.leitorReservador = leitorReservador;
        this.livroReservado = livroReservado;
    }

    /**
     * Método toString da reserva para exibição de seus atributos
     * @return String contento os atributos da reserva
     */
    @Override
    public String toString() {
        return "Reserva{" +
                "reservaID=" + reservaID +
                ", livroReservado=" + livroReservado +
                ", leitorReservador=" + leitorReservador +
                '}';
    }

    /**
     * Método equals da reserva
     * @param o objeto reserva
     * @return booleano resultante da comparação
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva reserva)) return false;
        return Objects.equals(getReservaID(), reserva.getReservaID());
    }
}
