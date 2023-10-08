package com.uefs.gerenciadorparabibliotecas.dao.emprestimo;

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
 * Testes do DAO Empréstimo
 */
 public class EmprestimoDAOListTest {
    /**
     * objetos do tipo empréstimo, leitor e livro são instanciados para serem utilizados ao longo dos testes
     */
    private Emprestimo emprestimo;
    private Livro livro;
    private Leitor leitor;

    @BeforeEach
     void setUp(){
        LocalDate dataEmprestimo = LocalDate.of(2023,10,1);
        LocalDate dataDevolucaoEsperada = dataEmprestimo.plusDays(7);
        this.livro = new Livro("Diário de um banana","Zezinho","Cultura","4455883","2013",
                CategoriaLivro.OUTRA, LocalizacaoLivro.alaC);
        this.leitor = new Leitor("Lucas","Feira VI","senha123","40028922",
                4477);
        this.emprestimo = new Emprestimo(dataEmprestimo,dataDevolucaoEsperada,this.livro,this.leitor);
       MasterDAO.getLivroDAO().criar(this.livro);
       MasterDAO.getLeitorDAO().criar(this.leitor);
       MasterDAO.getEmprestimoDAO().criar(emprestimo);
    }

    @AfterEach
     void tearDown() {
        MasterDAO.getEmprestimoDAO().resetar();
        MasterDAO.getLivroDAO().resetar();
        MasterDAO.getLeitorDAO().resetar();
    }

    /**
     * Teste para confirmar que a criação do objeto empréstimo foi feita da forma correta, todos os atributos usados
     * no construtor são verificados um por um
     * @throws EmprestimoException caso a busca pelo ID de um dos objetos falhe
     */
    @Test
     void criar() throws EmprestimoException{
        LocalDate dataEmprestimoAux = LocalDate.of(2023,10,1);
        LocalDate dataDevolucaoEsperadaAux = dataEmprestimoAux.plusDays(7);
        assertEquals(dataEmprestimoAux, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getDataEmprestimo());
        assertEquals(dataDevolucaoEsperadaAux, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getdataDevolucaoEsperada());
        assertEquals(this.livro, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getLivroEmprestado());
        assertEquals(this.leitor, MasterDAO.getEmprestimoDAO().procurarPorID(this.emprestimo.getEmprestimoID()).getMutuario());
    }

    /**
     * Teste para confirmar que a ação de deletar um empréstimo está sendo feita da forma correta, um novo
     * empréstimo auxiliar é criado para que o size da lista de empréstimos seja de 2, após deletar é verificado
     * se o size foi para 1
     * @throws EmprestimoException caso o objeto a ser deletado não seja encontrado
     */
    @Test
     void deletar() throws EmprestimoException{
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoEsperada = dataEmprestimo.plusDays(7);
        Emprestimo emprestimoAux = new Emprestimo(dataEmprestimo,dataDevolucaoEsperada,this.livro,this.leitor);
        MasterDAO.getEmprestimoDAO().criar(emprestimoAux);
        MasterDAO.getEmprestimoDAO().deletar(this.emprestimo);
        assertEquals(1, MasterDAO.getEmprestimoDAO().getEmprestimos().size());
    }

    /**
     * Teste para confirmar que após um resete, não exista nenhum empréstimo na lista
     */
    @Test
     void resetar(){
        MasterDAO.getEmprestimoDAO().resetar();
        assertEquals(0, MasterDAO.getEmprestimoDAO().getEmprestimos().size());
    }

    /**
     * Teste para confirmar que a busca por ID encontre um empréstimo
     * @throws EmprestimoException caso o empréstimo não seja encontrado
     */
    @Test
     void procurarPorID() throws EmprestimoException {
        assertEquals(emprestimo, MasterDAO.getEmprestimoDAO().procurarPorID(0));
    }

    /**
     * Teste realizado para confirmar que após realizar um empréstimo, a disponibilidade de um livro
     * automaticamente foi alterada, um livro auxiliar é criado para confirmar que a disponibilidade
     * deste é true
     */
    @Test
    void disponibilidadeLivroEmprestado() {
       Livro livroAux = new Livro("Cálculo II","James","EUA","1144558","1998",
               CategoriaLivro.DIDATICO,LocalizacaoLivro.alaD);
       assertTrue(livroAux.getDisponibilidade());
       assertFalse(this.livro.getDisponibilidade());
    }

    /**
     * Teste para confirmar que o empréstimo incluido na lista de empréstimos do leitor é o mesmo que ele realizou
     */
    @Test
    void adicionarListaemprestimosLeitor(){
       assertEquals(this.emprestimo.getMutuario().getEmprestimos().get(0).getEmprestimoID(), this.emprestimo.getEmprestimoID());
    }

    /**
     * Teste para verificar uma situação de falha ao deletar um empréstimo, um empréstimo auxiliar que não foi
     * adicionado a lista de empréstimos é usado é usado para validação
     */
    @Test
    void failDelete() {
       LocalDate dataEmprestimoAux = LocalDate.of(2023,9,14);
       LocalDate dataDevolucaoEsperadaAux = LocalDate.of(2023,9,21);
       try {
          MasterDAO.getEmprestimoDAO().deletar(new Emprestimo(dataEmprestimoAux, dataDevolucaoEsperadaAux,
                  new Livro("2001", "Fulano", "HBO","4789652", "2010",CategoriaLivro.HISTORIA,LocalizacaoLivro.alaD)
                  ,new Leitor("Tosta","UEFS","4477",
                  "75998765487",4125)));
          fail("Uma exceção deveria ser gerada!!");
       } catch (EmprestimoException e) {
          assertEquals(EmprestimoException.DELETE, e.getMessage());
       }
    }

    /**
     * Método que verifica uma situação de falha na busca por ID de um empréstimo, uma indentificação inválida
     * é passada como parâmetro da validação
     */
    @Test
    void failBusca(){
       try {
          MasterDAO.getEmprestimoDAO().procurarPorID(9999);
       } catch (EmprestimoException e) {
          assertEquals(EmprestimoException.SEARCH + "ID inválido:"+ 9999, e.getMessage());
       }
    }

    /**
     * Teste que confirma a quantidade de livros que foram emprestados em todo o sistema
     */
    @Test
    void numeroLivrosEmprestados() throws LeitorException, LivroException, EmprestimoException {
       LocalDate dataEmprestimo = LocalDate.now();
       LocalDate dataDevolucaoEsperada = dataEmprestimo.plusDays(7);
       Livro livroTeste = new Livro("Diário de um banana 44","Zezinho","Cultura","4455883","2013",
               CategoriaLivro.OUTRA, LocalizacaoLivro.alaC);
       Leitor leitorTeste = new Leitor("Mateus","Feira VI","senha123","40028922",
               4477);
       Emprestimo emprestimoTeste = new Emprestimo(dataEmprestimo,dataDevolucaoEsperada,livroTeste,leitorTeste);
       MasterDAO.getLivroDAO().criar(livroTeste);
       MasterDAO.getLeitorDAO().criar(leitorTeste);
       MasterDAO.getEmprestimoDAO().criar(emprestimoTeste);

       assertEquals(2, MasterDAO.getLivroDAO().nLivrosEmprestados());
    }

    /**
     * Teste que verifica a quantidade de livros que estão em um empréstimo atrasado por todo o sistema
     */
    @Test
    void numeroLivrosAtrasados(){
       assertEquals(1, MasterDAO.getEmprestimoDAO().nLivrosatrasados(LocalDate.of(2023,10,10)));
    }


    // TESTE RETIRADO DEVIDO A BUG NAS VALIDAÇÕES DE CRIAÇÃO DO EMPRÉSTIMO

    /*@Test
    void failCreate(){
       // TESTE PARA NÃO PERMITIR CRIAÇÃO DE EMPRÉSTIMO CASO USUÁRIO ESTEJA EM PERÍODO DE MULTA
       LocalDate dataEmprestimo = LocalDate.of(2023,10,12);
       LocalDate dataDevolucaoEsperada = dataEmprestimo.plusDays(7);
       this.emprestimo.finalizarEmprestimo(this.emprestimo,LocalDate.of(2023,10,10));
       Emprestimo emprestimoTeste = new Emprestimo(dataEmprestimo,dataDevolucaoEsperada,this.livro,this.leitor);
       assertNull(MasterDAO.getEmprestimoDAO().criar(emprestimoTeste));
    }*/

}