package com.uefs.gerenciadorparabibliotecas.dao.funcionario;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.model.Administrador;
import com.uefs.gerenciadorparabibliotecas.model.Bibliotecario;
import com.uefs.gerenciadorparabibliotecas.model.Funcionario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class FuncionarioDAOListTest {

    private Funcionario funcionarioAdm;
    private Funcionario funcionarioBiblio;
    @BeforeEach
     void beforeAll() {
        this.funcionarioBiblio = new Bibliotecario("Luva de pedreiro","BA","1234",
                "77998765012",7777);
        this.funcionarioAdm = new Administrador("Bora Bill","BA","4321",
                "7782560864",3567);
        MasterDAO.getFuncionarioDAO().criar(this.funcionarioAdm);
        MasterDAO.getFuncionarioDAO().criar(this.funcionarioBiblio);
    }

    @AfterEach
     void tearDown() {
        MasterDAO.getFuncionarioDAO().resetar();
    }

    @Test
     void criar() {
        assertEquals("Luva de pedreiro",MasterDAO.getFuncionarioDAO().procurarPorID(7777).getNome());
        assertEquals("Bora Bill",MasterDAO.getFuncionarioDAO().procurarPorID(3567).getNome());
        assertEquals("1234",MasterDAO.getFuncionarioDAO().procurarPorID(7777).getSenha());
        assertEquals("4321",MasterDAO.getFuncionarioDAO().procurarPorID(3567).getSenha());
        assertEquals("77998765012",MasterDAO.getFuncionarioDAO().procurarPorID(7777).getNumeroDeTelefone());
        assertEquals("7782560864",MasterDAO.getFuncionarioDAO().procurarPorID(3567).getNumeroDeTelefone());
        assertEquals("BA",MasterDAO.getFuncionarioDAO().procurarPorID(7777).getEndereco());
        assertEquals("BA",MasterDAO.getFuncionarioDAO().procurarPorID(3567).getEndereco());
    }

    @Test
     void deletar() {
        Funcionario funcionarioAdm2 = new Administrador("Bora Bill","BA","4321",
                "7782560864",3568);
        MasterDAO.getFuncionarioDAO().criar(funcionarioAdm2);
        MasterDAO.getFuncionarioDAO().deletar(7777);
        assertNotNull(MasterDAO.getFuncionarioDAO().procurarPorID(3568));
        assertNull(MasterDAO.getFuncionarioDAO().procurarPorID(7777));
    }

    @Test
     void resetar() {
        Funcionario funcionarioBiblio2 = new Bibliotecario("Luva de pedreiro","BA","1234",
                "77998765012",9988);
        MasterDAO.getFuncionarioDAO().criar(funcionarioBiblio2);
        MasterDAO.getFuncionarioDAO().resetar();
        assertNull(MasterDAO.getFuncionarioDAO().procurarPorID(7777));
        assertNull(MasterDAO.getFuncionarioDAO().procurarPorID(3567));
        assertNull(MasterDAO.getFuncionarioDAO().procurarPorID(9988));
    }

    @Test
     void procurarPorID() {
        assertEquals(this.funcionarioAdm,MasterDAO.getFuncionarioDAO().procurarPorID(3567));
        assertEquals(this.funcionarioBiblio, MasterDAO.getFuncionarioDAO().procurarPorID(7777));
    }
}