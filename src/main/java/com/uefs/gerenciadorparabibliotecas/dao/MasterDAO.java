package com.uefs.gerenciadorparabibliotecas.dao;

import com.uefs.gerenciadorparabibliotecas.dao.emprestimo.*;
import com.uefs.gerenciadorparabibliotecas.dao.funcionario.*;
import com.uefs.gerenciadorparabibliotecas.dao.leitor.*;
import com.uefs.gerenciadorparabibliotecas.dao.livro.*;
import com.uefs.gerenciadorparabibliotecas.dao.reserva.ReservaDAO;
import com.uefs.gerenciadorparabibliotecas.dao.reserva.ReservaDAOList;

/**
 * Padrão singleton para que exista um única instância para todos os objetos do DAO
 * @return objeto DAO criado (reserva, livro, leitor, funcionário e empréstimo)
 */

public class MasterDAO {
    private static EmprestimoDAO emprestimoDAO;
    private static FuncionarioDAO funcionarioDAO;
    private static LeitorDAO leitorDAO;
    private static LivroDAO livroDAO;

    private static ReservaDAO reservaDAO;

    public static ReservaDAO getReservaDAO(){
        if(reservaDAO == null){
            reservaDAO = new ReservaDAOList();
        }
        return reservaDAO;
    }
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
