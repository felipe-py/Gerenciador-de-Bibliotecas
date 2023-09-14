package com.uefs.gerenciadorparabibliotecas.dao;

import com.uefs.gerenciadorparabibliotecas.dao.emprestimo.*;
import com.uefs.gerenciadorparabibliotecas.dao.funcionario.*;
import com.uefs.gerenciadorparabibliotecas.dao.leitor.*;
import com.uefs.gerenciadorparabibliotecas.dao.livro.*;

public class MasterDAO {
    private static EmprestimoDAO emprestimoDAO;
    private static FuncionarioDAO funcionarioDAO;
    private static LeitorDAO leitorDAO;
    private static LivroDAO livroDAO;

    public static EmprestimoDAO getEmprestimoDAO() {
        if (emprestimoDAO == null) {
            emprestimoDAO = new EmprestimoDAOList();
        }
        return emprestimoDAO;
    }
    public static FuncionarioDAO getFuncionarioDAO() {
        if (funcionarioDAO == null) {
            funcionarioDAO = new FuncionarioDAOList();
        }
        return funcionarioDAO;
    }
    public static LeitorDAO getLeitorDAO() {
        if (leitorDAO == null) {
            leitorDAO = new LeitorDAOList();
        }
        return leitorDAO;
    }
    public static LivroDAO getLivroDAO() {
        if (livroDAO == null) {
            livroDAO = new LivroDAOList();
        }
        return livroDAO;
    }
}
