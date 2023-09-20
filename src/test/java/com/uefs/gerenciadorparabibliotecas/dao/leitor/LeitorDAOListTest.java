package com.uefs.gerenciadorparabibliotecas.dao.leitor;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeitorDAOListTest {

    private Leitor leitor;
    @BeforeEach
    void beforeAll(){
        this.leitor = new Leitor("Lucas","Feira VI","senha123","40028922",
                4477);
        MasterDAO.getLeitorDAO().criar(this.leitor);
    }

    @AfterEach
    void tearDown() {
        MasterDAO.getLeitorDAO().resetar();
    }

    @Test
    void criar(){
        assertEquals("Lucas",MasterDAO.getLeitorDAO().procurarPorID(4477).getNome());
        assertEquals("Feira VI",MasterDAO.getLeitorDAO().procurarPorID(4477).getEndereco());
        assertEquals("senha123",MasterDAO.getLeitorDAO().procurarPorID(4477).getSenha());
        assertEquals("40028922",MasterDAO.getLeitorDAO().procurarPorID(4477).getNumeroDeTelefone());
        assertEquals(4477,MasterDAO.getLeitorDAO().procurarPorID(4477).getUserID());
    }

    @Test
    void deletar() {
        Leitor leitorAux = new Leitor("Felipe","UEFS","senha321","40045001",
                8899);
        MasterDAO.getLeitorDAO().criar(leitorAux);

        MasterDAO.getLeitorDAO().deletar(this.leitor);
        assertNull(MasterDAO.getLeitorDAO().procurarPorID(this.leitor.getUserID()));
        assertNotNull(MasterDAO.getLeitorDAO().procurarPorID(leitorAux.getUserID()));
    }

    @Test
    void resetar() {
        MasterDAO.getLeitorDAO().resetar();
        assertNull(MasterDAO.getLeitorDAO().procurarPorID(this.leitor.getUserID()));
    }

    @Test
    void procurarPorID() {
        assertEquals(leitor, MasterDAO.getLeitorDAO().procurarPorID(4477));
    }
}