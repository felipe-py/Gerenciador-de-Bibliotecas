package com.uefs.gerenciadorparabibliotecas.dao.livro;

import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.model.CategoriaLivro;
import com.uefs.gerenciadorparabibliotecas.model.Livro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    public void deletar(Livro livro) throws LivroException {
        boolean remove = this.livros.remove(livro);
        if (!remove) {
            throw new LivroException(LivroException.DELETE, livro);
        }
    }

    @Override
    public void resetar() {
        this.livros = new ArrayList<>();
        this.novoID = 0;
    }
    @Override
    public Livro atualizar(Livro livro){
        int livroID = livro.getLivroID();

        for (int i = 0; i < livros.size(); i++) {
            Livro livroNaLista = livros.get(i);
            if (livroNaLista.getLivroID() == livroID) {
                livros.set(i, livro);
                return livro;
            }
        }
        return null;
    }
    @Override
    public Livro procurarPorID(Integer id) throws LivroException{
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

    @Override
    public List<Livro> livrosEncontrados(List<Livro> livrosAchados) throws LivroException{
        if (livrosAchados.isEmpty()) {
            throw new LivroException(LivroException.SEARCH);
        } else {
            return livrosAchados;
        }
    }

    @Override
    public int nLivrosEmprestados() {
        int soma = 0;
        for(Livro livro: this.livros){
            if(!livro.getDisponibilidade()){
                soma ++;
            }
        }
        return soma;
    }

    @Override
    public Integer nLivrosReservados() {
        Integer soma = 0;
        for(Livro livro: this.livros){
            if(livro.estaReservado()){
                soma += 1;
            }
        }
        return soma;
    }

    @Override
    public List<Livro> livrosPopulares() {
        // Agrupando os livros por ISBN
        Map<String, List<Livro>> livrosPorISBN = livros.stream()
                .collect(Collectors.groupingBy(Livro::getISBN));

        // Obtendo uma lista com os livros mais emprestados
        List<Livro> livrosMaisEmprestados = new ArrayList<>();
        livrosMaisEmprestados.addAll(livrosPorISBN.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));

        // Classificando os livros em ordem decrescente de número de empréstimos
        livrosMaisEmprestados.sort(Comparator.comparingInt(Livro::getNumeroEmprestimos).reversed());

        return livrosMaisEmprestados;
    }
}
