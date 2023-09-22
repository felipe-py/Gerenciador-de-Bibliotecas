package com.uefs.gerenciadorparabibliotecas.dao.livro;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.model.CategoriaLivro;
import com.uefs.gerenciadorparabibliotecas.model.Livro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LivroMasterDAOListTest {
    private Livro livro;

    @BeforeEach
    void setUp() {
        this.livro = new Livro("Diário de um banana","Zezinho","Cultura","4455883",
                "2013",CategoriaLivro.OUTRA,"ala c");
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
        assertEquals("ala c", MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getLocalizacao());
    }

    @Test
    void buscarPorID() throws LivroException{
        assertEquals(livro, MasterDAO.getLivroDAO().procurarPorID(0));
    }
    @Test
    void buscarPorISBN(){
        Livro livroAux2 = new Livro("Diário de um banana","Zezinho","Cultura",
                "4455783","2013", CategoriaLivro.OUTRA,"ala c");
        MasterDAO.getLivroDAO().criar(livroAux2);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorISBN("4455883");
        assertNotEquals(testeAux1,testeAux2);
    }

    @Test
    void buscarPorAutor(){
        Livro livroAux3 = new Livro("Diário de um banana 2","Zezinho","Cultura",
                "4455999","2014", CategoriaLivro.OUTRA,"ala c");
        MasterDAO.getLivroDAO().criar(livroAux3);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorAutor("Zezinho");
        assertEquals(testeAux2,testeAux1);
    }

    @Test
    void buscarPorTitulo(){
        Livro livroAux4 = new Livro("Diário de um banana 2","Zezinho","Cultura",
                "4455999","2014", CategoriaLivro.OUTRA,"ala c");
        MasterDAO.getLivroDAO().criar(livroAux4);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorTitulo("Diário de um banana");
        assertNotEquals(testeAux2,testeAux1);
    }

    @Test
    void buscarPorCategoria(){
        Livro livroAux5 = new Livro("Diário de um banana 2","Zezinho","Cultura",
                "4455999","2014", CategoriaLivro.OUTRA,"ala c");
        MasterDAO.getLivroDAO().criar(livroAux5);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorCategoria(CategoriaLivro.OUTRA);
        assertEquals(testeAux2,testeAux1);
    }


    @Test
    void deletar() throws LivroException {
        Livro livroAux = new Livro("Harry Potter 1","A chata","Warner","8899452"
                ,"2001",
                CategoriaLivro.FANTASIA,"ala d");
        MasterDAO.getLivroDAO().criar(livroAux);

        MasterDAO.getLivroDAO().deletar(this.livro);

        assertNull(MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()));
        assertNotNull(MasterDAO.getLivroDAO().procurarPorID(livroAux.getLivroID()));
    }

    @Test
    void resetar() throws LivroException{
        Livro livroAux = new Livro("Harry Potter 1","A chata","Warner","8899452","2001",
                CategoriaLivro.FANTASIA,"ala d");
        MasterDAO.getLivroDAO().criar(livroAux);

        MasterDAO.getLivroDAO().resetar();
        assertNull(MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()));
        assertNull(MasterDAO.getLivroDAO().procurarPorID(livroAux.getLivroID()));
    }

    @Test
    void atualizar() throws LivroException{
        Livro livroAux2 = new Livro("Harry Potter 6","A CHATA","Warner Bros","9999999","2008",
                CategoriaLivro.FANTASIA,"ala f");
        MasterDAO.getLivroDAO().criar(livroAux2);
        MasterDAO.getLivroDAO().atualizar(1,"Senhor dos Anéis","JR","Warner Pictures",
                "4455889","2006",CategoriaLivro.FANTASIA,"ala r");
        assertEquals("Senhor dos Anéis", MasterDAO.getLivroDAO().procurarPorID(livroAux2.getLivroID()).getTitulo());
        assertEquals("JR", MasterDAO.getLivroDAO().procurarPorID(livroAux2.getLivroID()).getautor());
        assertEquals("Warner Pictures", MasterDAO.getLivroDAO().procurarPorID(livroAux2.getLivroID()).getEditora());
        assertEquals("4455889", MasterDAO.getLivroDAO().procurarPorID(livroAux2.getLivroID()).getISBN());
        assertEquals("2006", MasterDAO.getLivroDAO().procurarPorID(livroAux2.getLivroID()).getAnoDePublicacao());
        assertEquals(CategoriaLivro.FANTASIA, MasterDAO.getLivroDAO().procurarPorID(livroAux2.getLivroID()).getCategoria());
        assertEquals("ala r", MasterDAO.getLivroDAO().procurarPorID(livroAux2.getLivroID()).getLocalizacao());
    }

    @Test
    void failDelete() {
        try {
            MasterDAO.getLivroDAO().deletar(new Livro("2001", "Fulano", "HBO","4789652",
                    "2010",CategoriaLivro.HISTORIA,"ala d"));
            fail("Uma exceção deveria ser gerada!!");
        } catch (LivroException e) {
            assertEquals(LivroException.DELETE, e.getMessage());
        }
    }

    @Test
    void failBusca(){
        try {
            MasterDAO.getLivroDAO().procurarPorID(9999);
        } catch (LivroException e) {
            assertEquals(LivroException.SEARCH + "ID inválido:"+ 9999, e.getMessage());
        }
    }
}