package com.uefs.gerenciadorparabibliotecas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Livro {
    protected String titulo;
    protected String autor;
    protected String editora;
    protected String isbn;
    protected String anoDePublicacao;
    protected CategoriaLivro categoria;
    protected boolean disponibilidade = true;
    protected String localizacao;
    protected LocalDate estimativaDeDevolucao;
    protected int livroID;
    private boolean statusReserva = false;
    protected List<Emprestimo> historicoDeEmprestimos;
    public Livro (String novoTitulo, String novoAutor, String novaEditora, String novoIsbn,
                  String novoAnoDePublicacao, CategoriaLivro novaCategoria, String novaLocalizacao) {
        this.titulo = novoTitulo;
        this.autor = novoAutor;
        this.editora = novaEditora;
        this.isbn = novoIsbn;
        this.anoDePublicacao = novoAnoDePublicacao;
        this.categoria = novaCategoria;
        this.localizacao = novaLocalizacao;
    }

    public boolean isStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(boolean statusReserva) {
        this.statusReserva = statusReserva;
    }

    public String getTitulo () {
        return this.titulo;
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
    public String getLocalizacao () {
        return this.localizacao;
    }
    public LocalDate getEstimativaDeDevolucao () {
        return this.estimativaDeDevolucao;
    }
    public int getLivroID () {
        return this.livroID;
    }
    public List<Emprestimo> getHistorico () {
        return this.historicoDeEmprestimos;
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
    public void setLocalizacao (String novaLocalizacao) {
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
