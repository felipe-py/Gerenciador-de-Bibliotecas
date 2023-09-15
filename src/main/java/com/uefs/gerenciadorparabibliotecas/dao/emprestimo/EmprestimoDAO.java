package com.uefs.gerenciadorparabibliotecas.dao.emprestimo;

import com.uefs.gerenciadorparabibliotecas.dao.CRUD;
import com.uefs.gerenciadorparabibliotecas.model.Emprestimo;

import java.util.List;

public interface EmprestimoDAO extends CRUD<Emprestimo> {
    List<Emprestimo> getEmprestimos();
}
