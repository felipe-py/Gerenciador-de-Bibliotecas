package com.uefs.gerenciadorparabibliotecas.dao.emprestimo;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.model.CategoriaLivro;
import com.uefs.gerenciadorparabibliotecas.model.Emprestimo;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;
import com.uefs.gerenciadorparabibliotecas.model.Livro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

 public class EmprestimoDAOListTest {

    private Emprestimo emprestimo;
    private Livro livro;
    private Leitor leitor;
    @BeforeEach
     void beforeAll() {
        LocalDate dataEmprestimo = LocalDate.of(2023,9,14);
        LocalDate dataDevolucao = LocalDate.of(2023,9,21);
        this.livro = new Livro("Diário de um banana","Zezinho","Cultura","4455883","2013",
                CategoriaLivro.OUTRA,"ala c");
        this.leitor = new Leitor("Lucas","Feira VI","senha123","40028922",
                4477);
        this.emprestimo = new Emprestimo(dataEmprestimo,dataDevolucao,this.livro,this.leitor);
        MasterDAO.getEmprestimoDAO().criar(emprestimo);
    }

    @AfterEach
     void tearDown() {
        MasterDAO.getEmprestimoDAO().resetar();
    }

    @Test
     void criar() {
        LocalDate dataEmprestimoAux = LocalDate.of(2023,9,14);
        LocalDate dataDevolucaoAux = LocalDate.of(2023,9,21);
        assertEquals(dataEmprestimoAux, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getDataEmprestimo());
        assertEquals(dataDevolucaoAux, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getDataDevolucao());
        assertEquals(this.livro, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getLivroEmprestado());
        assertEquals(this.leitor, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getMutuario());
    }

    @Test
     void deletar() {
        LocalDate dataEmprestimo = LocalDate.of(2023,9,14);
        LocalDate dataDevolucao = LocalDate.of(2023,9,21);
        Emprestimo emprestimoAux = new Emprestimo(dataEmprestimo,dataDevolucao,this.livro,this.leitor);
        MasterDAO.getEmprestimoDAO().criar(emprestimoAux);
        MasterDAO.getEmprestimoDAO().deletar(this.emprestimo.getEmprestimoID());
        assertNull(MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()));
        assertNotNull(MasterDAO.getEmprestimoDAO().procurarPorID(emprestimoAux.getEmprestimoID()));
    }

    @Test
     void resetar() {
        MasterDAO.getEmprestimoDAO().resetar();
        assertNull(MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()));
    }

    @Test
     void procurarPorID() {
        assertEquals(emprestimo, MasterDAO.getEmprestimoDAO().procurarPorID(0));
    }

    @Test
    void disponibilidadeLivroEmprestado() {
       Livro livroAux = new Livro("Cálculo II","James","EUA","1144558","1998",
               CategoriaLivro.DIDATICO,"ala d");
       assertTrue(livroAux.getDisponibilidade());
       assertFalse(this.livro.getDisponibilidade());
    }

    @Test
    void adicionarListaemprestimosLeitor(){
       System.out.println(this.emprestimo.getMutuario().getEmprestimos());
    }
}