package com.uefs.gerenciadorparabibliotecas.dao.leitor;

import com.uefs.gerenciadorparabibliotecas.dao.CRUD;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;

import java.util.List;

public interface LeitorDAO extends CRUD<Leitor> {
    List<Leitor> getLeitores();
}