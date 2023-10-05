package com.uefs.gerenciadorparabibliotecas.dao.livro;

import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.model.CategoriaLivro;
import com.uefs.gerenciadorparabibliotecas.model.Livro;

import java.util.*;
import java.util.stream.Collectors;

/**
 * CRUD e acesso aos dados dos livros
 */
public class LivroDAOList implements LivroDAO{

    private List<Livro> livros  = new ArrayList<>();
    private Integer novoID = 0;

    public LivroDAOList() {
        this.livros = livros;
        this.novoID = novoID;
    }
    /**
     * Retorna todas os livros cadastradas no sistema
     * @return lista com livros
     */
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

    /**
     *Método para buscar livros de um ISBN
     * @param ISBN
     * @return lista de todos os livros que possuem o ISBN informado
     */
    @Override
    public List<Livro> procurarPorISBN(String ISBN){
        List<Livro> livrosISBN = livros.stream()
                .filter(a -> a.getISBN().equals(ISBN))
                .collect(Collectors.toList());
        return  livrosISBN;
    }

    /**
     *Método para buscar livros de um autor
     * @param autor
     * @return lista de todos os livros que possuem o autor informado no seu campo Autor
     */
    @Override
    public List<Livro> procurarPorAutor(String autor){
        List<Livro> livrosAutor = livros.stream()
                .filter(a -> a.getautor().contains(autor))
                .collect(Collectors.toList());
        return  livrosAutor;
    }

    /**
     *Método para buscar livros de um título
     * @param titulo
     * @return lista de todos os livros com o título informado
     */
    @Override
    public List<Livro> procurarPorTitulo(String titulo){
        List<Livro> livrosTitulo = livros.stream()
                .filter(a -> a.getTitulo().equals(titulo))
                .collect(Collectors.toList());
        return  livrosTitulo;
    }

    /**
     * Método para buscar livros de uma categoria
     * @param categoriaLivro
     * @return lista com todos os livros que se enqudram na categoria informada
     */
    @Override
    public List<Livro> procurarPorCategoria(CategoriaLivro categoriaLivro){
        List<Livro> livrosCategoria = livros.stream()
                .filter(a -> a.getCategoria().equals(categoriaLivro))
                .collect(Collectors.toList());
        return  livrosCategoria;
    }

    /**
     * Método que que verifica se a lista de livros encontrados em busca por autor, título, isbn ou categoria
     * está preenchida ou vazia
     * @param livrosAchados
     * @return livros encontrados em algum dos métodos de busca que não envolvem o ID do livro
     * @throws LivroException caso a lista retornada esteja vazia (Nenhum livro com o atributo informado foi encontrado)
     */
    @Override
    public List<Livro> livrosEncontrados(List<Livro> livrosAchados) throws LivroException{
        if (livrosAchados.isEmpty()) {
            throw new LivroException(LivroException.SEARCH);
        } else {
            return livrosAchados;
        }
    }

    /**
     * Método que realiza o somatório de todos os livros que estão emprestados no sistema
     * @return o somatório de todos os livros que estão emprestados, utiliza o atributo disponibilidade do livro
     */
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

    /**
     *Método que realiza o somatório de todos os livros reservados no sistema
     * @return o somatório com o número de livros que estão reservados
     */
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

    /**
     * Responsável por agrupar os livros pelo seu ISBN em uma estrutura chave-valor. Isso facilita a forma
     * como é calculado os livros mais populares
     * @return map que possui como chave o ISBN e valor uma lista com todos os livros que possuem aquele ISBN
     */
    @Override
    public Map<String, List<Livro>> agruparLivrosPorISBN() {
        Map<String, List<Livro>> livrosAgrupados = new HashMap<>();

        for (Livro livro : livros) {
            String isbn = livro.getISBN();
            if (!livrosAgrupados.containsKey(isbn)) {
                livrosAgrupados.put(isbn, new ArrayList<>());
            }
            livrosAgrupados.get(isbn).add(livro);
        }
        return livrosAgrupados;
    }

    /**
     *Método para calcular os livros mais emprestados da biblioteca
     * @param livrosPorIsbn map com os livros agrupados por ISBN
     * @return map dos livros mais emprestados e o seu número de empréstimo, em ordem decrescente
     */
    @Override
    public Map<Integer, List<String>> livrosPopulares(Map<String, List<Livro>> livrosPorIsbn) {
        Map<Integer, List<Livro>> livrosPorEmprestimos = new TreeMap<>(Collections.reverseOrder());
        Map<Integer, List<String>> resultado = new TreeMap<>(Collections.reverseOrder());

        // Percorre o map original e adiciona os livros ao novo map
        for (Map.Entry<String, List<Livro>> entry : livrosPorIsbn.entrySet()) {
            int totalEmprestimos = 0;
            for (Livro livro : entry.getValue()) {
                totalEmprestimos += livro.getNumeroEmprestimos();
            }
            livrosPorEmprestimos.put(totalEmprestimos, entry.getValue());
        }

        // Monta os dados para retorno
        for (Map.Entry<Integer, List<Livro>> entry : livrosPorEmprestimos.entrySet()) {
            List<Livro> livros = entry.getValue();
            if (!livros.isEmpty()) {
                String titulo = livros.get(0).getTitulo();
                List<String> emprestimos = resultado.getOrDefault(entry.getKey(), new ArrayList<>());
                emprestimos.add(titulo);
                resultado.put(entry.getKey(), emprestimos);
            }
        }
        return resultado;
    }
}
