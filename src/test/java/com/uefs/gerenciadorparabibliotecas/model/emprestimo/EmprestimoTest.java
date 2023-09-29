package com.uefs.gerenciadorparabibliotecas.model.emprestimo;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions.EmprestimoException;
import com.uefs.gerenciadorparabibliotecas.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoTest {
    private Emprestimo emprestimo;
    private Livro livro;
    private Leitor leitor;
    @BeforeEach
    void setUp() {
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucao = dataEmprestimo.plusDays(7);
        this.livro = new Livro("Diário de um banana","Zezinho","Cultura","4455883","2013",
                CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
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
    void estenderEmprestimoRenovacoes() throws EmprestimoException {
        this.emprestimo.estenderEmprestimo(this.emprestimo);
        assertEquals(1, emprestimo.getNumeroDeRenovacoes());
    }

    @Test
    void estenderEmprestimoFalhaRenovacoes01() throws EmprestimoException {
        // TESTE CASO UM LEITOR TENTE RENOVAR MAIS DE UMA VEZ
        try {
            this.emprestimo.estenderEmprestimo(this.emprestimo);
            this.emprestimo.estenderEmprestimo(this.emprestimo);
            fail("Uma exceção deveria ser gerada!!");
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.RENEW, e.getMessage());
        }
    }

    @Test
    void estenderEmprestimoFalhaRenovacoes02() throws EmprestimoException {
        // TESTE CASO UM LEITOR TENTE RENOVAR UM EMPRÁTIMO COM LIVRO RESERVADO
        Leitor leitorAux = new Leitor("Lucas","Feira VI","senha123","40028922",
                44710);
        Reserva reserva = new Reserva(leitorAux, this.livro);
        MasterDAO.getReservaDAO().criar(reserva);
        try {
            this.emprestimo.estenderEmprestimo(this.emprestimo);
            fail("Uma exceção deveria ser gerada!!");
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.RENEW, e.getMessage());
        }
    }
}