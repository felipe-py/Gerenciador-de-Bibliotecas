package com.uefs.gerenciadorparabibliotecas.dao;

import com.uefs.gerenciadorparabibliotecas.dao.pessoa.PessoaDAO;
import com.uefs.gerenciadorparabibliotecas.dao.pessoa.PessoaDAOList;
import com.uefs.gerenciadorparabibliotecas.dao.usuario.UsuarioDAO;
import com.uefs.gerenciadorparabibliotecas.dao.usuario.UsuarioDAOList;
import com.uefs.gerenciadorparabibliotecas.dao.emprestimo.EmprestimoDAO;
import com.uefs.gerenciadorparabibliotecas.dao.emprestimo.EmprestimoDAOList;
import com.uefs.gerenciadorparabibliotecas.dao.livro.LivroDAO;
import com.uefs.gerenciadorparabibliotecas.dao.livro.LivroDAOList;

public class MasterDAO {
    private static PessoaDAO pessoaDAO;
    private static UsuarioDAO usuarioDAO;
    private static EmprestimoDAO emprestimoDAO;
    private static LivroDAO livroDAO;

    public static PessoaDAO getPessoaDAO() {
        if (pessoaDAO == null) {
            pessoaDAO = new PessoaDAOList();
        }
        return pessoaDAO;
    }

    public static EmprestimoDAO getEmprestimoDAO() {
        if (emprestimoDAO == null) {
            emprestimoDAO = new EmprestimoDAOList();
        }
        return emprestimoDAO;
    }

    public static LivroDAO getLivroDAO() {
        if (livroDAO == null) {
            livroDAO = new LivroDAOList();
        }
        return livroDAO;
    }

    public static UsuarioDAO getUsuarioDAO() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAOList();
        }
        return usuarioDAO;
    }
}
