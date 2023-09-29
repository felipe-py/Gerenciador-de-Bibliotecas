package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.emprestimo.EmprestimoDAOListTest;
import com.uefs.gerenciadorparabibliotecas.dao.funcionario.FuncionarioDAOListTest;
import com.uefs.gerenciadorparabibliotecas.dao.leitor.LeitorDAOListTest;
import com.uefs.gerenciadorparabibliotecas.dao.livro.LivroMasterDAOListTest;
import com.uefs.gerenciadorparabibliotecas.dao.reserva.ReservaDAOListTest;
import com.uefs.gerenciadorparabibliotecas.model.emprestimo.EmprestimoTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite de testes")
@SelectClasses(value = {
        EmprestimoDAOListTest.class,
        FuncionarioDAOListTest.class,
        LeitorDAOListTest.class,
        LivroMasterDAOListTest.class,
        ReservaDAOListTest.class,
        EmprestimoTest.class
})
public class TestSuite {
}


