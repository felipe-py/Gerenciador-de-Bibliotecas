package com.uefs.gerenciadorparabibliotecas.dao;

import com.uefs.gerenciadorparabibliotecas.dao.emprestimo.*;
import com.uefs.gerenciadorparabibliotecas.dao.funcionario.*;
import com.uefs.gerenciadorparabibliotecas.dao.leitor.*;
import com.uefs.gerenciadorparabibliotecas.dao.livro.*;
import com.uefs.gerenciadorparabibliotecas.dao.reserva.ReservaDAO;
import com.uefs.gerenciadorparabibliotecas.dao.reserva.ReservaDAOFile;
import com.uefs.gerenciadorparabibliotecas.dao.reserva.ReservaDAOList;

/**
 * Objetos de cada classe DAO, que serão utilizados para acessar os seus respectivos dados
 * EmprestimoDAO para os empréstimos,
 * FuncionárioDAO para os funcionários,
 * LeitorDAO para os leitores,
 * ReservaDAO para as reservas,
 */

public class MasterDAO {

    private static EmprestimoDAO emprestimoDAO;
    private static FuncionarioDAO funcionarioDAO;
    private static LeitorDAO leitorDAO;
    private static LivroDAO livroDAO;
    private static ReservaDAO reservaDAO;

    /**
     * Método para acessar o DAO reserva
     * @return objeto reservaDAO
     */
    public static ReservaDAO getReservaDAO(){
        if(reservaDAO == null){
            reservaDAO = new ReservaDAOFile("src/main/dados/reservas.bin");
        }
        return reservaDAO;
    }
    /**
     * Método para acessar o DAO empréstimo
     * @return objeto empréstimoDAO
     */
    public static EmprestimoDAO getEmprestimoDAO() {
        if (emprestimoDAO == null) {
            emprestimoDAO = new EmprestimoDAOFile("src/main/dados/emprestimos.bin");
        }
        return emprestimoDAO;
    }
    /**
     * Método para acessar o DAO funcionário
     * @return objeto funcionárioDAO
     */
    public static FuncionarioDAO getFuncionarioDAO() {
        if (funcionarioDAO == null) {
            funcionarioDAO = new FuncionarioDAOFile("src/main/dados/funcionarios.bin");
        }
        return funcionarioDAO;
    }
    /**
     * Método para acessar o DAO leitor
     * @return objeto leitorDAO
     */
    public static LeitorDAO getLeitorDAO() {
        if (leitorDAO == null) {
            leitorDAO = new LeitorDAOFile("src/main/dados/leitores.bin");
        }
        return leitorDAO;
    }
    /**
     * Método para acessar o DAO livro
     * @return objeto livroDAO
     */
    public static LivroDAO getLivroDAO() {
        if (livroDAO == null) {
            livroDAO = new LivroDAOFile("src/main/dados/livros.bin");
        }
        return livroDAO;
    }
}
