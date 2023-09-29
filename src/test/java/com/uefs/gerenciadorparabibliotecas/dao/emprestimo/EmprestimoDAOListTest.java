package com.uefs.gerenciadorparabibliotecas.dao.emprestimo;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions.EmprestimoException;
import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.model.*;
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
     void setUp(){
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucao = dataEmprestimo.plusDays(7);
        this.livro = new Livro("Diário de um banana","Zezinho","Cultura","4455883","2013",
                CategoriaLivro.OUTRA, LocalizacaoLivro.alaC);
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
     void criar() throws EmprestimoException{
        LocalDate dataEmprestimoAux = LocalDate.now();
        LocalDate dataDevolucaoAux = dataEmprestimoAux.plusDays(7);
        assertEquals(dataEmprestimoAux, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getDataEmprestimo());
        assertEquals(dataDevolucaoAux, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getDataDevolucao());
        assertEquals(this.livro, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getLivroEmprestado());
        assertEquals(this.leitor, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getMutuario());
    }

    @Test
     void deletar() throws EmprestimoException{
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucao = dataEmprestimo.plusDays(7);
        Emprestimo emprestimoAux = new Emprestimo(dataEmprestimo,dataDevolucao,this.livro,this.leitor);
        MasterDAO.getEmprestimoDAO().criar(emprestimoAux);
        MasterDAO.getEmprestimoDAO().deletar(this.emprestimo);
        assertEquals(1, MasterDAO.getEmprestimoDAO().getEmprestimos().size());
    }

    @Test
     void resetar() throws EmprestimoException{
        MasterDAO.getEmprestimoDAO().resetar();
        assertEquals(0, MasterDAO.getEmprestimoDAO().getEmprestimos().size());
    }

    @Test
     void procurarPorID() throws EmprestimoException {
        assertEquals(emprestimo, MasterDAO.getEmprestimoDAO().procurarPorID(0));
    }

    @Test
    void disponibilidadeLivroEmprestado() {
       Livro livroAux = new Livro("Cálculo II","James","EUA","1144558","1998",
               CategoriaLivro.DIDATICO,LocalizacaoLivro.alaD);
       assertTrue(livroAux.getDisponibilidade());
       assertFalse(this.livro.getDisponibilidade());
    }

    @Test
    void adicionarListaemprestimosLeitor(){
       System.out.println(this.emprestimo.getMutuario().getEmprestimos());
    }

    @Test
    void failDelete() {
       LocalDate dataEmprestimoAux = LocalDate.of(2023,9,14);
       LocalDate dataDevolucaoAux = LocalDate.of(2023,9,21);
       try {
          MasterDAO.getEmprestimoDAO().deletar(new Emprestimo(dataEmprestimoAux, dataDevolucaoAux,
                  new Livro("2001", "Fulano", "HBO","4789652", "2010",CategoriaLivro.HISTORIA,LocalizacaoLivro.alaD)
                  ,new Leitor("Tosta","UEFS","4477",
                  "75998765487",4125)));
          fail("Uma exceção deveria ser gerada!!");
       } catch (EmprestimoException e) {
          assertEquals(EmprestimoException.DELETE, e.getMessage());
       }
    }

    @Test
    void failBusca(){
       try {
          MasterDAO.getEmprestimoDAO().procurarPorID(9999);
       } catch (EmprestimoException e) {
          assertEquals(EmprestimoException.SEARCH + "ID inválido:"+ 9999, e.getMessage());
       }
    }
}