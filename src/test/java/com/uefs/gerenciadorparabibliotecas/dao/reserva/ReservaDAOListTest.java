package com.uefs.gerenciadorparabibliotecas.dao.reserva;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.model.CategoriaLivro;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;
import com.uefs.gerenciadorparabibliotecas.model.Livro;
import com.uefs.gerenciadorparabibliotecas.model.Reserva;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaDAOListTest {
    private Reserva reserva;
    private Livro livro;
    private Leitor leitor;

    @BeforeEach
    void beforeAll(){
        this.livro = new Livro("Asassin's Creed","Não sei","HBO","4455223",
                "1999", CategoriaLivro.FANTASIA,"ala a");
        this.leitor = new Leitor("Carlos","Asa Branca","4455","75982560864",
                7896);
        this.reserva = new Reserva(this.leitor, this.livro);
        MasterDAO.getReservaDAO().criar(this.reserva);
    }

    @AfterEach
    void tearDown(){
        MasterDAO.getReservaDAO().resetar();
    }

    @Test
    void criar() {
        assertEquals(this.leitor, MasterDAO.getReservaDAO().procurarPorID(this.reserva.getReservaID()).getLeitorReservador());
        assertEquals(this.livro, MasterDAO.getReservaDAO().procurarPorID(this.reserva.getReservaID()).getLivroReservado());
    }

    @Test
    void deletar() {
        Leitor leitorAux = new Leitor("Pedrin","Novo Horizonte","5566","77985241369",
                7825);
        Livro livroAux = new Livro("Asassin's Creed","Não sei","HBO","4455223",
                "1999", CategoriaLivro.FANTASIA,"ala a");

        Reserva reservaAux = new Reserva(leitorAux, livroAux);
        MasterDAO.getReservaDAO().criar(reservaAux);
        MasterDAO.getReservaDAO().deletar(reservaAux.getReservaID());
        System.out.println(MasterDAO.getReservaDAO().getReservas());
        assertNotNull(MasterDAO.getReservaDAO().procurarPorID(this.reserva.getReservaID()));
        assertNull(MasterDAO.getReservaDAO().procurarPorID(reservaAux.getReservaID()));
    }

    @Test
    void resetar() {
        Leitor leitorAux = new Leitor("Pedrin","Novo Horizonte","5566","77985241369",
                7825);
        Livro livroAux = new Livro("Asassin's Creed","Não sei","HBO","4455223",
                "1999", CategoriaLivro.FANTASIA,"ala a");

        Reserva reservaAux = new Reserva(leitorAux, livroAux);
        MasterDAO.getReservaDAO().criar(reservaAux);
        MasterDAO.getReservaDAO().resetar();

        assertNull(MasterDAO.getReservaDAO().procurarPorID(this.reserva.getReservaID()));
        assertNull(MasterDAO.getReservaDAO().procurarPorID(reservaAux.getReservaID()));
    }

    @Test
    void procurarPorID() {
        assertEquals(this.reserva, MasterDAO.getReservaDAO().procurarPorID(0));
    }
}