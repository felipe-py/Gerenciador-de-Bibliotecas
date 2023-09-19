package com.uefs.gerenciadorparabibliotecas.model;

public class Reserva {

    protected Integer reservaID;
    private Livro livroReservado;

    private Leitor leitorReservador;

    public Integer getReservaID() {
        return reservaID;
    }

    public void setReservaID(Integer reservaID) {

        this.reservaID = reservaID;
    }

    public Livro getLivroReservado() {
        return livroReservado;
    }

    public void setLivroReservado(Livro livroReservado) {
        this.livroReservado = livroReservado;
    }

    public Leitor getLeitorReservador() {
        return leitorReservador;
    }

    public void setLeitorReservador(Leitor leitorReservador) {
        this.leitorReservador = leitorReservador;
    }

    public Reserva(Leitor leitorReservador, Livro livroReservado) {
        this.leitorReservador = leitorReservador;
        this.livroReservado = livroReservado;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "reservaID=" + reservaID +
                ", livroReservado=" + livroReservado +
                ", leitorReservador=" + leitorReservador +
                '}';
    }
}
