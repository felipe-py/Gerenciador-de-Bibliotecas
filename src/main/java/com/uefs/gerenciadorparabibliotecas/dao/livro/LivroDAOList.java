package com.uefs.gerenciadorparabibliotecas.dao.livro;

import com.uefs.gerenciadorparabibliotecas.model.CategoriaLivro;
import com.uefs.gerenciadorparabibliotecas.model.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LivroDAOList implements LivroDAO{

    private List<Livro> livros  = new ArrayList<>();
    private Integer novoID = 0;

    public LivroDAOList() {
        this.livros = livros;
        this.novoID = novoID;
    }
    @Override
    public List<Livro> getLivros() {
        return livros;
    }
    @Override
    public Livro criar(Livro livro) {
        livro.setLivroID(this.novoID);
        this.novoID++;
        this.livros.add(livro);
        return livro;
    }

    @Override
    public void deletar(Livro livro) {
        boolean remove = this.livros.remove(livro);
    }

    @Override
    public void resetar() {
        this.livros = new ArrayList<>();
        this.novoID = 0;
    }
    @Override
    public void atualizar(Integer id, String novoTitulo, String novoAutor, String novaEditora, String novoISBN,
                          String novaData, CategoriaLivro novaCategoria, String novaLocalizacao) {

        for(Livro livro: this.livros){
            if(livro.getLivroID() == id){
                livro.setAutor(novoAutor);
                livro.setCategoria(novaCategoria);
                livro.setEditora(novaEditora);
                livro.setLocalizacao(novaLocalizacao);
                livro.setAnoDePublicacao(novaData);
                livro.setIsbn(novoISBN);
                livro.setTitulo(novoTitulo);
            } else {
                System.out.println("...");
            }
        }
    }

    @Override
    public Livro procurarPorID(Integer id) {
        for (Livro livro : this.livros) {
            if (livro.getLivroID() == id) {
                return livro;
            }
        }
        return null;
    }
    @Override
    public List<Livro> procurarPorISBN(String ISBN){
        List<Livro> livrosISBN = livros.stream()
                .filter(a -> a.getISBN().equals(ISBN))
                .collect(Collectors.toList());
        return  livrosISBN;
    }
    @Override
    public List<Livro> procurarPorAutor(String autor){
        List<Livro> livrosAutor = livros.stream()
                .filter(a -> a.getautor().contains(autor))
                .collect(Collectors.toList());
        return  livrosAutor;
    }
    @Override
    public List<Livro> procurarPorTitulo(String titulo){
        List<Livro> livrosTitulo = livros.stream()
                .filter(a -> a.getTitulo().equals(titulo))
                .collect(Collectors.toList());
        return  livrosTitulo;
    }
    @Override
    public List<Livro> procurarPorCategoria(CategoriaLivro categoriaLivro){
        List<Livro> livrosCategoria = livros.stream()
                .filter(a -> a.getCategoria().equals(categoriaLivro))
                .collect(Collectors.toList());
        return  livrosCategoria;
    }
}
