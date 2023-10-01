package com.uefs.gerenciadorparabibliotecas.dao.livro;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LivroMasterDAOListTest {
    private Livro livro;

    @BeforeEach
    void setUp() throws LivroException{
        this.livro = new Livro("Diário de um banana","Zezinho","Cultura","4455883",
                "2013",CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
        MasterDAO.getLivroDAO().criar(this.livro);

    }

    @AfterEach
    void tearDown() {
        MasterDAO.getLivroDAO().resetar();
    }

    @Test
    void criar() throws LivroException{
        assertEquals("Diário de um banana", MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getTitulo());
        assertEquals("Zezinho", MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getautor());
        assertEquals("Cultura", MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getEditora());
        assertEquals("4455883", MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getISBN());
        assertEquals("2013", MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getAnoDePublicacao());
        assertEquals(CategoriaLivro.OUTRA, MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getCategoria());
        assertEquals(LocalizacaoLivro.alaC, MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getLocalizacao());
    }

    @Test
    void buscarPorID() throws LivroException{
        assertEquals(livro, MasterDAO.getLivroDAO().procurarPorID(0));
    }
    @Test
    void buscarPorISBN() {
        Livro livroAux2 = new Livro("Diário de um banana","Zezinho","Cultura",
                "4455783","2013", CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
        MasterDAO.getLivroDAO().criar(livroAux2);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorISBN("4455883");
        assertNotEquals(testeAux1,testeAux2);
    }
    @Test
    void buscarPorAutor() throws LivroException{
        Livro livroAux3 = new Livro("Diário de um banana 2","Zezinho","Cultura",
                "4455999","2014", CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
        MasterDAO.getLivroDAO().criar(livroAux3);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorAutor("Zezinho");
        assertEquals(testeAux2,testeAux1);
    }

    @Test
    void buscarPorTitulo() {
        Livro livroAux4 = new Livro("Diário de um banana 2","Zezinho","Cultura",
                "4455999","2014", CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
        MasterDAO.getLivroDAO().criar(livroAux4);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorTitulo("Diário de um banana");
        assertNotEquals(testeAux2,testeAux1);
    }

    @Test
    void buscarPorCategoria() throws LivroException{
        Livro livroAux5 = new Livro("Diário de um banana 2","Zezinho","Cultura",
                "4455999","2014", CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
        MasterDAO.getLivroDAO().criar(livroAux5);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorCategoria(CategoriaLivro.OUTRA);
        assertEquals(testeAux2,testeAux1);
    }


    @Test
    void deletar() throws LivroException {
        Livro livroAux = new Livro("Harry Potter 1","A chata","Warner","8899452"
                ,"2001",
                CategoriaLivro.FANTASIA,LocalizacaoLivro.alaD);
        MasterDAO.getLivroDAO().criar(livroAux);

        MasterDAO.getLivroDAO().deletar(this.livro);

        assertNull(MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()));
        assertNotNull(MasterDAO.getLivroDAO().procurarPorID(livroAux.getLivroID()));
    }

    @Test
    void resetar() throws LivroException{
        Livro livroAux = new Livro("Harry Potter 1","A chata","Warner","8899452","2001",
                CategoriaLivro.FANTASIA,LocalizacaoLivro.alaD);
        MasterDAO.getLivroDAO().criar(livroAux);
        MasterDAO.getLivroDAO().resetar();
        assertNull(MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()));
        assertNull(MasterDAO.getLivroDAO().procurarPorID(livroAux.getLivroID()));
    }

    @Test
    void atualizar() throws LivroException{
        Livro livroAux2 = new Livro("Harry Potter 6","A CHATA","Warner Bros","9999999","2008",
                CategoriaLivro.FANTASIA,LocalizacaoLivro.alaF);
        MasterDAO.getLivroDAO().criar(livroAux2);
        this.livro.setTitulo("Casinha");
        this.livro.setIsbn("444444444");
        this.livro.setLocalizacao(LocalizacaoLivro.alaC);
        this.livro.setAutor("pedrinho");
        this.livro.setEditora("casa");
        this.livro.setAnoDePublicacao("1345");
        this.livro.setCategoria(CategoriaLivro.HISTORIA);
        Livro atual = MasterDAO.getLivroDAO().atualizar(this.livro);
        assertEquals(this.livro, atual);
    }

    @Test
    void failDelete() {
        try {
            MasterDAO.getLivroDAO().deletar(new Livro("2001", "Fulano", "HBO","4789652",
                    "2010",CategoriaLivro.HISTORIA,LocalizacaoLivro.alaD));
            fail("Uma exceção deveria ser gerada!!");
        } catch (LivroException e) {
            assertEquals(LivroException.DELETE, e.getMessage());
        }
    }

    @Test
    void failBuscaID(){
        try {
            MasterDAO.getLivroDAO().procurarPorID(9999);
        } catch (LivroException e) {
            assertEquals(LivroException.SEARCH + "9999" + "inválido(a)", e.getMessage());
        }
    }

    @Test
    void failBuscaAutor(){
        try{
            MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorAutor("Zezinho"));
        } catch (LivroException e) {
            assertEquals(LivroException.SEARCH + "Informação enviada inválida.", e.getMessage());
        }
    }

    @Test
    void failBuscaISBN(){
        try{
            MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorISBN("00000000"));
        } catch (LivroException e) {
            assertEquals(LivroException.SEARCH + "Informação enviada inválida.", e.getMessage());
        }
    }

    @Test
    void failBuscaTitulo(){
        try{
            MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorTitulo("00000000"));
        } catch (LivroException e) {
            assertEquals(LivroException.SEARCH + "Informação enviada inválida.", e.getMessage());
        }
    }

    @Test
    void failBuscaCategoria(){
        try{
            MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorCategoria(CategoriaLivro.POESIA));
        } catch (LivroException e) {
            assertEquals(LivroException.SEARCH + "Informação enviada inválida.", e.getMessage());
        }
    }

    @Test
    void livrosPopulares(){

        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoEsperada = dataEmprestimo.plusDays(7);

        Livro livro01 = new Livro("Harry Potter e a Pedra Filosofal","Zezinho","Cultura","12345678","2013",
                CategoriaLivro.OUTRA, LocalizacaoLivro.alaC);
        Leitor leitor01 = new Leitor("Lucas","Feira VI","senha123","40028922",
                1010);
        Emprestimo emprestimo01 = new Emprestimo(dataEmprestimo,dataDevolucaoEsperada,livro01,leitor01);

        Livro livro02 = new Livro("Harry Potter e a Pedra Filosofal","Zezinho","Cultura","12345678","2013",
                CategoriaLivro.OUTRA, LocalizacaoLivro.alaC);
        Leitor leitor02 = new Leitor("Felipe","Feira VI","senha123","40028922",
                1011);
        Emprestimo emprestimo02 = new Emprestimo(dataEmprestimo,dataDevolucaoEsperada,livro02,leitor02);

        Livro livro04 = new Livro("Diário de um banana 2","Zezinho","Cultura","4455884","2013",
                CategoriaLivro.OUTRA, LocalizacaoLivro.alaC);
        Leitor leitor04 = new Leitor("Lucas","Feira VI","senha123","40028922",
                1013);
        Emprestimo emprestimo04 = new Emprestimo(dataEmprestimo,dataDevolucaoEsperada,livro04,leitor04);

        MasterDAO.getLivroDAO().criar(livro01);
        MasterDAO.getLivroDAO().criar(livro02);
        MasterDAO.getLivroDAO().criar(livro04);

        MasterDAO.getLeitorDAO().criar(leitor01);
        MasterDAO.getLeitorDAO().criar(leitor02);
        MasterDAO.getLeitorDAO().criar(leitor04);

        MasterDAO.getEmprestimoDAO().criar(emprestimo01);
        MasterDAO.getEmprestimoDAO().criar(emprestimo02);
        MasterDAO.getEmprestimoDAO().criar(emprestimo04);
        MasterDAO.getEmprestimoDAO().criar(emprestimo04);
        MasterDAO.getEmprestimoDAO().criar(emprestimo04);

        System.out.println("\n= TESTE DOS LIVROS MAIS POPULARES =\n");
        System.out.println("OBSERVAÇÕES\n VALORES DIFEREM ENTRE RODAR COM TESTE SUITE E REALIZAR ESTE TESTE UNITARIAMENTE");

        int soma = 1;
        for (Map.Entry<Integer, List<String>> entry : MasterDAO.getLivroDAO().livrosPopulares(MasterDAO.getLivroDAO().agruparLivrosPorISBN()).entrySet()) {
            System.out.println(soma + "º: Número de empréstimos => " + entry.getKey() + " || Livro: " + entry.getValue());
            soma++;
        }
    }
}