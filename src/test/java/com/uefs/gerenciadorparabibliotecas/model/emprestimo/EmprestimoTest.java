package com.uefs.gerenciadorparabibliotecas.model.emprestimo;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions.EmprestimoException;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes do model Empréstimo
 */
public class EmprestimoTest {
    /**
     * objetos do tipo empréstimo, livro e leitor são criados para utilização ao longo dos testes
     */
    private Emprestimo emprestimo;
    private Livro livro;
    private Leitor leitor;
    @BeforeEach
    void setUp() throws LeitorException, EmprestimoException, LivroException {
        // DATA DEFINIDA MANUALMENTE PARA FACILITAR CALCULO DO ATRASO
        LocalDate dataEmprestimo = LocalDate.of(2023,10,1);
        LocalDate dataDevolucaoEsperada = dataEmprestimo.plusDays(7);
        this.livro = new Livro("Diário de um banana","Zezinho","Cultura","4455883","2013",
                CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
        this.leitor = new Leitor("Lucas","Feira VI","senha123","40028922",
                4477);
        this.emprestimo = new Emprestimo(dataEmprestimo,dataDevolucaoEsperada,this.livro,this.leitor);
        MasterDAO.getEmprestimoDAO().criar(emprestimo);
    }

    @AfterEach
    void tearDown() {
        MasterDAO.getEmprestimoDAO().resetar();
        MasterDAO.getReservaDAO().resetar();
    }

    /**
     * Teste para confirmar que o método que estende um empréstimo funciona corretamente,
     * confirmando que este atributo foi de 0 para 1 automaticamente
     * @throws EmprestimoException caso o limite de renovações tenha sido atingido
     */
    @Test
    void estenderEmprestimoRenovacoes() throws EmprestimoException {
        this.emprestimo.estenderEmprestimo(this.emprestimo);
        assertEquals(1, emprestimo.getNumeroDeRenovacoes());
    }

    /**
     * Teste para confirmar que a falha no método que estende um empréstimo, confirmando que este
     * atingiu o seu limite de renovações
     */
    @Test
    void estenderEmprestimoFalhaRenovacoes01()  {
        // TESTE CASO UM LEITOR TENTE RENOVAR MAIS DE UMA VEZ
        try {
            this.emprestimo.estenderEmprestimo(this.emprestimo);
            this.emprestimo.estenderEmprestimo(this.emprestimo);
            fail("Uma exceção deveria ser gerada!!");
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.RENEW, e.getMessage());
        }
    }

    /**
     * Teste para confirmar que a falha no método que estende um empréstimo, confirmando que o
     * livro que está no empréstimo foi reservado
     */
    @Test
    void estenderEmprestimoFalhaRenovacoes02() throws LeitorException {
        // TESTE CASO UM LEITOR TENTE RENOVAR UM EMPRÉSTIMO COM LIVRO RESERVADO
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

    /**
     * Método que verifica todas as questões relacionadas a finalização de um empréstimo em atraso,
     * verificando a quantidade de dias em atraso e a multa em dias que será dada ao leitor que o atrasou
     */
    @Test
    void finalizarEmprestimoAtrasado(){
        this.emprestimo.finalizarEmprestimo(this.emprestimo, LocalDate.of(2023,10,10));
        assertEquals(-2,this.emprestimo.getAtraso(),"Dias do atraso empréstimo (NEGATIVADO)");
        assertEquals(4,this.emprestimo.getMutuario().getDiasDeMulta(), "Dias de multa do leitor, o dobro do atraso");
        assertEquals(LocalDate.of(2023,10,14),this.leitor.getDataDoFimDaMulta());
    }

    /**
     * Método que verifica todas as questões relacionadas a finalização de um empréstimo sem atraso,
     * verificando a quantidade de dias em atraso e a multa em dias que será dada ao leitor que o atrasou,
     * neste caso, 0.
     */
    @Test
    void finalizarEmprestimoEmDia(){
        this.emprestimo.finalizarEmprestimo(this.emprestimo, LocalDate.of(2023,10,7));
        assertEquals(0,this.emprestimo.getAtraso());
        assertEquals(0,this.emprestimo.getMutuario().getDiasDeMulta());
        assertTrue(this.emprestimo.getLivroEmprestado().getDisponibilidade());
    }
}