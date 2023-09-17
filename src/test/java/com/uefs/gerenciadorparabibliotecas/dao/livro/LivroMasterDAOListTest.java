package com.uefs.gerenciadorparabibliotecas.dao.livro;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.model.CategoriaLivro;
import com.uefs.gerenciadorparabibliotecas.model.Livro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LivroMasterDAOListTest {
    private Livro livro;

    @BeforeEach
    void beforeAll() {
        LocalDate data = LocalDate.of(1998, 1, 1);

        this.livro = new Livro("Diário de um banana","Zezinho","Cultura","4455883",data,CategoriaLivro.OUTRA,"ala c");
        MasterDAO.getLivroDAO().criar(this.livro);

    }

    @AfterEach
    void tearDown() {
        MasterDAO.getLivroDAO().resetar();
    }

    @Test
    void criar() {
        LocalDate data = LocalDate.of(1998, 1, 1);
        assertEquals("Diário de um banana", MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getTitulo());
        assertEquals("Zezinho", MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getautor());
        assertEquals("Cultura", MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getEditora());
        assertEquals("4455883", MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getISBN());
        assertEquals(data, MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getAnoDePublicacao());
        assertEquals(CategoriaLivro.OUTRA, MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getCategoria());
        assertEquals("ala c", MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()).getLocalizacao());
    }

    @Test
    void buscarPorID(){
        assertEquals(livro, MasterDAO.getLivroDAO().procurarPorID(0));
    }
    @Test
    void buscarPorISBN(){
        LocalDate data = LocalDate.of(1998, 1, 1);
        Livro livroAux2 = new Livro("Diário de um banana","Zezinho","Cultura","4455783",data, CategoriaLivro.OUTRA,"ala c");
        MasterDAO.getLivroDAO().criar(livroAux2);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorISBN("4455883");
        assertNotEquals(testeAux1,testeAux2);
    }

    @Test
    void buscarPorAutor(){
        LocalDate data = LocalDate.of(1998, 1, 1);
        Livro livroAux3 = new Livro("Diário de um banana 2","Zezinho","Cultura","4455999",data, CategoriaLivro.OUTRA,"ala c");
        MasterDAO.getLivroDAO().criar(livroAux3);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorAutor("Zezinho");
        assertEquals(testeAux2,testeAux1);
    }

    @Test
    void buscarPorTitulo(){
        LocalDate data = LocalDate.of(1998, 1, 1);
        Livro livroAux4 = new Livro("Diário de um banana 2","Zezinho","Cultura","4455999",data, CategoriaLivro.OUTRA,"ala c");
        MasterDAO.getLivroDAO().criar(livroAux4);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorTitulo("Diário de um banana");
        assertNotEquals(testeAux2,testeAux1);
    }

    @Test
    void buscarPorCategoria(){
        LocalDate data = LocalDate.of(1998, 1, 1);
        Livro livroAux5 = new Livro("Diário de um banana 2","Zezinho","Cultura","4455999",data, CategoriaLivro.OUTRA,"ala c");
        MasterDAO.getLivroDAO().criar(livroAux5);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorCategoria(CategoriaLivro.OUTRA);
        assertEquals(testeAux2,testeAux1);
    }


    @Test
    void deletar() {
        LocalDate data = LocalDate.of(2000, 1, 1);
        Livro livroAux = new Livro("Harry Potter 1","A chata","Warner","8899452",data,
                CategoriaLivro.FANTASIA,"ala d");
        MasterDAO.getLivroDAO().criar(livroAux);

        MasterDAO.getLivroDAO().deletar(this.livro.getLivroID());

        assertNull(MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()));
        assertNotNull(MasterDAO.getLivroDAO().procurarPorID(livroAux.getLivroID()));
    }

    @Test
    void resetar(){
        LocalDate data = LocalDate.of(2000, 1, 1);
        Livro livroAux = new Livro("Harry Potter 1","A chata","Warner","8899452",data,
                CategoriaLivro.FANTASIA,"ala d");
        MasterDAO.getLivroDAO().criar(livroAux);

        MasterDAO.getLivroDAO().resetar();
        assertNull(MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()));
        assertNull(MasterDAO.getLivroDAO().procurarPorID(livroAux.getLivroID()));
    }
}