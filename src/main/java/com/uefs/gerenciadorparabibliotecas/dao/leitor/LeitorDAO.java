package com.uefs.gerenciadorparabibliotecas.dao.leitor;

import com.uefs.gerenciadorparabibliotecas.dao.CRUD;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;

import java.util.List;
/**
 * Interface para o LeitorDAOList
 */
public interface LeitorDAO extends CRUD<Leitor, LeitorException> {
    List<Leitor> getLeitores();
    Leitor atualizar(Leitor leitor);
}
