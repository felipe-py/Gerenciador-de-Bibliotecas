package com.uefs.gerenciadorparabibliotecas.dao.livro;

import com.uefs.gerenciadorparabibliotecas.dao.CRUD;
import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.model.CategoriaLivro;
import com.uefs.gerenciadorparabibliotecas.model.Livro;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
/**
 * Interface para o LivroDAOList
 */
public interface LivroDAO extends CRUD<Livro, LivroException> {

    Livro atualizar(Livro livro);

    List<Livro> procurarPorISBN(String ISBN);
    List<Livro> procurarPorAutor(String autor);
    List<Livro> procurarPorTitulo(String titulo);
    List<Livro> procurarPorCategoria(CategoriaLivro categoriaLivro);
    List<Livro> getLivros();
    List<Livro> livrosEncontrados(List<Livro> livrosAchados) throws LivroException;
    int nLivrosEmprestados();
    Integer nLivrosReservados();
    Map<String, List<Livro>> agruparLivrosPorISBN();
    Map<Integer, List<String>> livrosPopulares(Map<String, List<Livro>> livrosPorIsbn);
}
