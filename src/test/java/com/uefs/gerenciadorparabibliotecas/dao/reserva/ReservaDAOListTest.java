package com.uefs.gerenciadorparabibliotecas.dao.reserva;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.reservaExcetions.ReservaException;
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
    void setUp(){
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
    void criar() throws ReservaException{
        assertEquals(this.leitor, MasterDAO.getReservaDAO().procurarPorID(this.reserva.getReservaID()).getLeitorReservador());
        assertEquals(this.livro, MasterDAO.getReservaDAO().procurarPorID(this.reserva.getReservaID()).getLivroReservado());
    }

    @Test
    void deletar() throws ReservaException {
        Leitor leitorAux = new Leitor("Pedrin","Novo Horizonte","5566","77985241369",
                7825);
        Livro livroAux = new Livro("Asassin's Creed","Não sei","HBO","4455223",
                "1999", CategoriaLivro.FANTASIA,"ala a");

        Reserva reservaAux = new Reserva(leitorAux, livroAux);
        MasterDAO.getReservaDAO().criar(reservaAux);
        MasterDAO.getReservaDAO().deletar(reservaAux);
        assertEquals(1, MasterDAO.getReservaDAO().getReservas().size());
    }

    @Test
    void resetar() throws ReservaException{
        Leitor leitorAux = new Leitor("Pedrin","Novo Horizonte","5566","77985241369",
                7825);
        Livro livroAux = new Livro("Asassin's Creed","Não sei","HBO","4455223",
                "1999", CategoriaLivro.FANTASIA,"ala a");

        Reserva reservaAux = new Reserva(leitorAux, livroAux);
        MasterDAO.getReservaDAO().criar(reservaAux);
        MasterDAO.getReservaDAO().resetar();
        assertEquals(0,MasterDAO.getReservaDAO().getReservas().size());
    }

    @Test
    void procurarPorID() throws ReservaException {
        assertEquals(this.reserva, MasterDAO.getReservaDAO().procurarPorID(0));
    }

    @Test
    void failDelete() {
        try {
            MasterDAO.getReservaDAO().deletar(new Reserva(new Leitor("Tosta","UEFS","4477",
                    "75998765487",4125), new Livro("Dados","Roberto",
                    "UEFS","4563217","2005",CategoriaLivro.DIDATICO, "ala e")));
            fail("Uma exceção deveria ser gerada!!");
        } catch (ReservaException e) {
            assertEquals(ReservaException.DELETE, e.getMessage());
        }
    }

    @Test
    void failBusca(){
        try {
            MasterDAO.getReservaDAO().procurarPorID(9999);
        } catch (ReservaException e) {
            assertEquals(ReservaException.SEARCH + "ID inválido:"+ 9999, e.getMessage());
        }
    }
}