package com.uefs.gerenciadorparabibliotecas.dao.funcionario;

import com.uefs.gerenciadorparabibliotecas.dao.CRUD;
import com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions.FuncionarioException;
import com.uefs.gerenciadorparabibliotecas.model.Funcionario;

import java.util.List;
/**
 * Interface para o FuncionárioDAOList
 */
public interface FuncionarioDAO extends CRUD<Funcionario, FuncionarioException> {
    List<Funcionario> getFuncionarios();
    Funcionario atualizar(Funcionario funcionario);
}
