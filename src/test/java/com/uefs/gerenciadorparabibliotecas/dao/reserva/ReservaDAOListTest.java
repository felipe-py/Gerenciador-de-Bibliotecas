package com.uefs.gerenciadorparabibliotecas.dao.reserva;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.exeptions.reservaExcetions.ReservaException;
import com.uefs.gerenciadorparabibliotecas.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testes do DAO Reserva
 */
public class ReservaDAOListTest {

    /**
     * objetos do tipo reserva, livro e leitor são criados para serem usados ao longo dos testes
     */
    private Reserva reserva;
    private Livro livro;
    private Leitor leitor;

    @BeforeEach
    void setUp() throws LeitorException, LivroException {
        this.livro = new Livro("Assasins Creed","Um cara","HBO","4455223",
                "1999", CategoriaLivro.FANTASIA, LocalizacaoLivro.alaD);
        this.leitor = new Leitor("Carlos","Asa Branca","4455","75982560864",
                7896);
        this.reserva = new Reserva(this.leitor, this.livro);
        MasterDAO.getLeitorDAO().criar(this.leitor);
        MasterDAO.getLivroDAO().criar(this.livro);
        MasterDAO.getReservaDAO().criar(this.reserva);
    }

    @AfterEach
    void tearDown(){
        MasterDAO.getReservaDAO().resetar();
        MasterDAO.getLivroDAO().resetar();
        MasterDAO.getLeitorDAO().resetar();
    }

    /**
     * Teste para confirmar que a criação do objeto reserva foi feita da forma correta, todos os atributos usados
     * no construtor são verificados um por um
     * @throws ReservaException caso a busca pelo ID de um dos objetos falhe
     */
    @Test
    void criar() throws ReservaException{
        assertEquals(this.reserva, MasterDAO.getReservaDAO().procurarPorID(0));
    }

    /**
     * Teste para confirmar que a ação de deletar uma reserva está sendo feita da forma correta, uma nova
     * reserva auxiliar é criado para que o size da lista seja de 2, após deletar é confirmado que o size é de 1
     * @throws ReservaException caso o objeto a ser deletado não seja encontrado
     */
    @Test
    void deletar() throws ReservaException, LeitorException, LivroException {
        Leitor leitorAux = new Leitor("Pedrin","Novo Horizonte","5566","77985241369",
                7825);
        Livro livroAux = new Livro("Assasins Creed","Um cara","HBO","4455223",
                "1999", CategoriaLivro.FANTASIA,LocalizacaoLivro.alaA);

        Reserva reservaAux = new Reserva(leitorAux, livroAux);
        MasterDAO.getReservaDAO().criar(reservaAux);
        MasterDAO.getReservaDAO().deletar(reservaAux);
        assertEquals(1, MasterDAO.getReservaDAO().getReservas().size());
    }

    /**
     * Teste para confirmar que após um resete, o size da lista de reseervas seja de 0
     */
    @Test
    void resetar() throws LeitorException, LivroException {
        Leitor leitorAux = new Leitor("Pedrin","Novo Horizonte","5566","77985241369",
                7825);
        Livro livroAux = new Livro("Assasins Creed","Um cara","HBO","4455223",
                "1999", CategoriaLivro.FANTASIA,LocalizacaoLivro.alaA);

        Reserva reservaAux = new Reserva(leitorAux, livroAux);
        MasterDAO.getReservaDAO().criar(reservaAux);
        MasterDAO.getReservaDAO().resetar();
        assertEquals(0,MasterDAO.getReservaDAO().getReservas().size());
    }

    /**
     * Teste para confirmar que a busca por ID encontre uma reserva
     * @throws ReservaException caso a reserva não seja encontrada
     */
    @Test
    void procurarPorID() throws ReservaException {
        assertEquals(this.reserva, MasterDAO.getReservaDAO().procurarPorID(0));
    }

    /**
     * Teste para verificar uma situação de falha ao deletar uma reserva, uma reserva auxiliar que não foi
     * adicionado a lista de reservas é usada para validação
     */
    @Test
    void failDelete() throws LeitorException, LivroException {
        try {
            MasterDAO.getReservaDAO().deletar(new Reserva(new Leitor("Tosta","UEFS","4477",
                    "75998765487",4125), new Livro("Dados","Roberto",
                    "UEFS","4563217","2005",CategoriaLivro.DIDATICO, LocalizacaoLivro.alaE)));
            fail("Uma exceção deveria ser gerada!!");
        } catch (ReservaException e) {
            assertEquals(ReservaException.DELETE, e.getMessage());
        }
    }

    /**
     * Método que verifica uma situação de falha na busca por ID de uma reserva, uma indentificação inválida
     * é passada como parâmetro da validação
     */
    @Test
    void failBusca(){
        try {
            MasterDAO.getReservaDAO().procurarPorID(9999);
        } catch (ReservaException e) {
            assertEquals(ReservaException.SEARCH + "ID inválido:"+ 9999, e.getMessage());
        }
    }

    /**
     * Teste para verificar o número de livros que estão reservados em todo o sistema
     */
    @Test
    void numeroLivrosReservados() throws LivroException, LeitorException, ReservaException {
        Livro livroTeste = new Livro("Assasins Creed","Um cara","HBO","4455223",
                "1999", CategoriaLivro.FANTASIA, LocalizacaoLivro.alaD);
        Leitor leitorTeste = new Leitor("Carlos","Asa Branca","4455","75982560864",
                7896);
        Reserva reservaTeste = new Reserva(leitorTeste, livroTeste);
        MasterDAO.getLeitorDAO().criar(leitorTeste);
        MasterDAO.getLivroDAO().criar(livroTeste);
        MasterDAO.getReservaDAO().criar(reservaTeste);
        assertEquals(2, MasterDAO.getLivroDAO().nLivrosReservados());
    }
}