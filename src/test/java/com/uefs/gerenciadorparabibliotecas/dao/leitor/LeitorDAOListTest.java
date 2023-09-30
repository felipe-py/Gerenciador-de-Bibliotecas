package com.uefs.gerenciadorparabibliotecas.dao.leitor;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;

import com.uefs.gerenciadorparabibliotecas.model.Leitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeitorDAOListTest {

    private Leitor leitor;
    @BeforeEach
    void setUp(){
        this.leitor = new Leitor("Lucas","Feira VI","senha123","40028922",
                4477);
        MasterDAO.getLeitorDAO().criar(this.leitor);
    }

    @AfterEach
    void tearDown() {
        MasterDAO.getLeitorDAO().resetar();
    }

    @Test
    void criar() throws LeitorException{
        assertEquals("Lucas",MasterDAO.getLeitorDAO().procurarPorID(4477).getNome());
        assertEquals("Feira VI",MasterDAO.getLeitorDAO().procurarPorID(4477).getEndereco());
        assertEquals("senha123",MasterDAO.getLeitorDAO().procurarPorID(4477).getSenha());
        assertEquals("40028922",MasterDAO.getLeitorDAO().procurarPorID(4477).getNumeroDeTelefone());
        assertEquals(4477,MasterDAO.getLeitorDAO().procurarPorID(4477).getUserID());
    }

    @Test
    void deletar() throws LeitorException {
        Leitor leitorAux = new Leitor("Felipe","UEFS","senha321","40045001",
                8899);
        MasterDAO.getLeitorDAO().criar(leitorAux);
        MasterDAO.getLeitorDAO().deletar(this.leitor);
        assertEquals(1, MasterDAO.getLeitorDAO().getLeitores().size());

    }

    @Test
    void resetar() throws LeitorException{
        MasterDAO.getLeitorDAO().resetar();
        assertEquals(0, MasterDAO.getLeitorDAO().getLeitores().size());
    }

    @Test
    void procurarPorID() throws LeitorException {
        assertEquals(leitor, MasterDAO.getLeitorDAO().procurarPorID(4477));
    }

    @Test
    void failDelete() {
        try {
            MasterDAO.getLeitorDAO().deletar(new Leitor("Felipe", "Parque Ipê", "4477"
                    ,"85474783",4122));
            fail("Uma exceção deveria ser gerada!!");
        } catch (LeitorException e) {
            assertEquals(LeitorException.DELETE, e.getMessage());
        }
    }

    @Test
    void atualizar(){
        this.leitor.setEndereco("Casa");
        this.leitor.setNome("Ana");
        this.leitor.setSenha("4444");
        this.leitor.setNumeroDeTelefone("414141414");
        this.leitor.setUserID(0000);
        Leitor atual = MasterDAO.getLeitorDAO().atualizar(this.leitor);
        assertEquals(atual, this.leitor);
    }

    @Test
    void failBusca() throws LeitorException{
        try {
            MasterDAO.getLeitorDAO().procurarPorID(9999);
        } catch (LeitorException e) {
            assertEquals(LeitorException.SEARCH + "ID inválido:"+ 9999, e.getMessage());
        }
    }
}