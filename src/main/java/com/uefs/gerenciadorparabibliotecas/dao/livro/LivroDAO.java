package com.uefs.gerenciadorparabibliotecas.dao.livro;

import com.uefs.gerenciadorparabibliotecas.dao.CRUD;
import com.uefs.gerenciadorparabibliotecas.model.CategoriaLivro;
import com.uefs.gerenciadorparabibliotecas.model.Livro;

import java.util.List;

public interface LivroDAO extends CRUD<Livro> {
    List<Livro> procurarPorISBN(String ISBN);
    List<Livro> procurarPorAutor(String autor);
    List<Livro> procurarPorTitulo(String titulo);
    List<Livro> procurarPorCategoria(CategoriaLivro categoriaLivro);
    List<Livro> getLivros();
}