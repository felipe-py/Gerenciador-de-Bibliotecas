package com.uefs.gerenciadorparabibliotecas.dao.funcionario;

import com.uefs.gerenciadorparabibliotecas.dao.CRUD;
import com.uefs.gerenciadorparabibliotecas.model.Funcionario;

import java.util.List;

public interface FuncionarioDAO extends CRUD<Funcionario> {
    List<Funcionario> getFuncionarios();
}
