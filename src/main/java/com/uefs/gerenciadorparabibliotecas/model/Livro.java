package com.uefs.gerenciadorparabibliotecas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Livro {
    private String titulo;
    private String autor;
    private String editora;
    private String isbn;
    private String anoDePublicacao;
    private CategoriaLivro categoria;
    private boolean disponibilidade = true;
    private LocalizacaoLivro localizacao;
    private LocalDate estimativaDeDevolucao;
    private int livroID;
    private boolean statusReserva = false;
    private Integer numeroEmprestimos = 0;

    /**
     * Construtor do livro carregando seus principais atributos de criação
     * @param novoTitulo
     * @param novoAutor
     * @param novaEditora
     * @param novoIsbn
     * @param novoAnoDePublicacao
     * @param novaCategoria
     * @param novaLocalizacao
     */
    public Livro (String novoTitulo, String novoAutor, String novaEditora, String novoIsbn,
                  String novoAnoDePublicacao, CategoriaLivro novaCategoria, LocalizacaoLivro novaLocalizacao) {
        this.titulo = novoTitulo;
        this.autor = novoAutor;
        this.editora = novaEditora;
        this.isbn = novoIsbn;
        this.anoDePublicacao = novoAnoDePublicacao;
        this.categoria = novaCategoria;
        this.localizacao = novaLocalizacao;
    }

    public boolean estaReservado() {
        return statusReserva;
    }
    public boolean naoEstaReservado() {
        return !statusReserva;
    }

    public void setStatusReserva(boolean statusReserva) {
        this.statusReserva = statusReserva;
    }

    public String getTitulo () {
        return this.titulo;
    }

    public Integer getNumeroEmprestimos() {
        return numeroEmprestimos;
    }

    public void setNumeroEmprestimos() {
        this.numeroEmprestimos += 1;
    }

    public String getautor () {
        return this.autor;
    }
    public String getEditora () {
        return this.editora;
    }
    public String getISBN () {
        return this.isbn;
    }
    public String getAnoDePublicacao () {
        return this.anoDePublicacao;
    }
    public CategoriaLivro getCategoria () {
        return this.categoria;
    }
    public boolean getDisponibilidade () {
        return this.disponibilidade;
    }
    public LocalizacaoLivro getLocalizacao () {
        return this.localizacao;
    }
    public LocalDate getEstimativaDeDevolucao () {
        return this.estimativaDeDevolucao;
    }
    public int getLivroID () {
        return this.livroID;
    }

    public void setTitulo (String novoTitulo) {
        this.titulo = novoTitulo;
    }
    public void setAutor (String novoAutor) {
        this.autor = novoAutor;
    }
    public void setEditora (String novaEditora) {
        this.editora = novaEditora;
    }
    public void setIsbn (String novoISBN) {
        this.isbn = novoISBN;
    }
    public void setCategoria (CategoriaLivro novaCategoria) {
        this.categoria = novaCategoria;
    }
    public void setAnoDePublicacao (String anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }
    public void setDisponibilidade (boolean novaDisponibilidade) {
        this.disponibilidade = novaDisponibilidade;
    }
    public void setLocalizacao (LocalizacaoLivro novaLocalizacao) {
        this.localizacao = novaLocalizacao;
    }
    public void setEstimativaDeDevolucao (LocalDate novaDataDeDevolucao) {
        this.estimativaDeDevolucao = novaDataDeDevolucao;
    }
    public void setLivroID(int livroID) {
        this.livroID = livroID;
    }

    public boolean equals(Livro l) {
        if ((l == null)|| getClass() != l.getClass()) {
            return false;
        }
        if (this == l) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Livro{" +
                "LivroID=" + livroID + '\''+
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editora='" + editora + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anoDePublicacao=" + anoDePublicacao +
                ", categoria=" + categoria +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}
