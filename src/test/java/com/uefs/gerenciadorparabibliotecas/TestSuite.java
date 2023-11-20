package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.emprestimo.EmprestimoDAOListTest;
import com.uefs.gerenciadorparabibliotecas.dao.funcionario.FuncionarioDAOListTest;
import com.uefs.gerenciadorparabibliotecas.dao.leitor.LeitorDAOListTest;
import com.uefs.gerenciadorparabibliotecas.dao.livro.LivroDAOListTest;
import com.uefs.gerenciadorparabibliotecas.dao.reserva.ReservaDAOListTest;
import com.uefs.gerenciadorparabibliotecas.model.EmprestimoTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Suite de testes para otimização das verificações
 */
@Suite
@SuiteDisplayName("Suite de testes")
@SelectClasses(value = {
        EmprestimoDAOListTest.class,
        FuncionarioDAOListTest.class,
        LeitorDAOListTest.class,
        LivroDAOListTest.class,
        ReservaDAOListTest.class,
        EmprestimoTest.class
})

public class TestSuite {
}