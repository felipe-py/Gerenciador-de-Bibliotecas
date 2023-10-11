package com.uefs.gerenciadorparabibliotecas.model;

import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;

import java.io.Serializable;

/**
 * Classe Model do livro
 */
public class Livro implements Serializable {
    /**
     * título: String que armazena o título do livro
     * autor: String que armazena o autor do livro
     * editora: String que armazena a editora do livro
     * ISBN: String que armazena o ISBN do livro
     * anoDePublicação: String que armazena o ano em que um livro foi publicado
     * Categoria: enum CategoriaLivro que armazena a categoria do livro
     * disponibilidade: booleano que armazena a disponibilidade do livro
     * localização: enum LocalizaçãoLivro que armazena a localização de um livro
     * livroID: inteiro que armazena a indentificação do livro
     * statusReserva: booleano que informa se um livro está reservado ou não
     * numeroEmpréstimos: Integer contador que armazena quantas vezes um livro foi emprestado
     */
    private String titulo;
    private String autor;
    private String editora;
    private String isbn;
    private String anoDePublicacao;
    private CategoriaLivro categoria;
    private boolean disponibilidade = true;
    private LocalizacaoLivro localizacao;
    private int livroID;
    private boolean statusReserva = false;
    private Integer numeroEmprestimos = 0;

    /**
     * Construtor do livro carregando seus principais atributos de criação
     * @param novoTitulo título do livro
     * @param novoAutor autor do livro
     * @param novaEditora editora do livro
     * @param novoIsbn isbn do livro
     * @param novoAnoDePublicacao ano de publicação do livro
     * @param novaCategoria categoria do livro
     * @param novaLocalizacao localização do livro
     * O método de validação é chamado para averiguar todos os atributos enviados
     */
    public Livro (String novoTitulo, String novoAutor, String novaEditora, String novoIsbn,
                  String novoAnoDePublicacao, CategoriaLivro novaCategoria, LocalizacaoLivro novaLocalizacao) throws LivroException {
        this.titulo = novoTitulo;
        this.autor = novoAutor;
        this.editora = novaEditora;
        this.isbn = novoIsbn;
        this.anoDePublicacao = novoAnoDePublicacao;
        this.categoria = novaCategoria;
        this.localizacao = novaLocalizacao;
        validarLivro();
    }

    /**
     * Método para veriicar se todos os atributos do livro informados na criação são válidos, é averiguado se
     * as informações estão vazias, se o nome do autor só possui letras e se o ano de publicação e o ISBN só
     * possuem números
     * @throws LivroException caso qualquer uma das validações não seja atendida
     */
    private void validarLivro() throws LivroException {
        if(this.getTitulo().isEmpty() || this.getautor().isEmpty() || this.getEditora().isEmpty() || this.getAnoDePublicacao().isEmpty() ||
        this.getISBN().isEmpty()){
            throw new LivroException(LivroException.EMPITY_INFO);
        }
        if(!(this.getautor().matches("^[a-zA-Z\\s]*$"))){
            throw new LivroException(LivroException.INVALID_INFO, this.getautor());
        }
        if(!(this.getAnoDePublicacao().matches("^[0-9\\s-]*$"))){
            throw new LivroException(LivroException.INVALID_INFO, this.getAnoDePublicacao());
        }
        if(!(this.getISBN().matches("^[0-9\\s-]*$"))){
            throw new LivroException(LivroException.INVALID_INFO, this.getISBN());
        }
    }

    /**
     * Método para verificar se um livro esta reservado
     * @return status da reserva do livro
     */
    public boolean estaReservado() {
        return statusReserva;
    }

    /**
     * Método para verificar se um livro não esta reservado
     * @return status da reserva do livro
     */
    public boolean naoEstaReservado() {
        return !statusReserva;
    }

    /**
     * Modifica o status de reserva do livro
     * @param statusReserva novo status para resrva do livro
     */
    public void setStatusReserva(boolean statusReserva) {
        this.statusReserva = statusReserva;
    }

    /**
     * Método para acessa o título do livro
     * @return título do livro
     */
    public String getTitulo () {
        return this.titulo;
    }

    /**
     * Método para acessa o número de empréstimos do livro
     * @return número de empréstimos do livro
     */
    public Integer getNumeroEmprestimos() {
        return numeroEmprestimos;
    }

    /**
     * Modifica o número de empréstimos de um livro, somando 1 cada vez que é instanciado
     */
    public void setNumeroEmprestimos() {
        this.numeroEmprestimos += 1;
    }

    /**
     * Método para acessa o autor do livro
     * @return autor do livro
     */
    public String getautor () {
        return this.autor;
    }

    /**
     * Método para acessa a editora do livro
     * @return editora do livro
     */
    public String getEditora () {
        return this.editora;
    }

    /**
     * Método para acessa o ISBN do livro
     * @return ISBN do livro
     */
    public String getISBN () {
        return this.isbn;
    }

    /**
     * Método para acessa o ano de publicação do livro
     * @return ano de publicação do livro do livro
     */
    public String getAnoDePublicacao () {
        return this.anoDePublicacao;
    }

    /**
     * Método para acessa a categoria do livro
     * @return categoria do livro
     */
    public CategoriaLivro getCategoria () {
        return this.categoria;
    }

    /**
     * Método para acessa a disponibilidade do livro
     * @return disponibilidade do livro
     */
    public boolean getDisponibilidade () {
        return this.disponibilidade;
    }

    /**
     * Método para acessa a localização do livro
     * @return localização do livro
     */
    public LocalizacaoLivro getLocalizacao () {
        return this.localizacao;
    }

    /**
     * Método para acessa o ID do livro
     * @return ID do livro
     */
    public int getLivroID () {
        return this.livroID;
    }

    /**
     * Modifica o título do livro
     * @param novoTitulo novo título para o livro
     */
    public void setTitulo (String novoTitulo) {
        this.titulo = novoTitulo;
    }

    /**
     * Modifica o autor do livro
     * @param novoAutor novo autor do livro
     */
    public void setAutor (String novoAutor) {
        this.autor = novoAutor;
    }

    /**
     * Modifica a editora do livro
     * @param novaEditora nova editora do livro
     */
    public void setEditora (String novaEditora) {
        this.editora = novaEditora;
    }

    /**
     * Modifica o ISBN do livro
     * @param novoISBN novo ISBN do livro
     */
    public void setIsbn (String novoISBN) {
        this.isbn = novoISBN;
    }

    /**
     * Modifica a categoria do livro
     * @param novaCategoria nova categoria do livro
     */
    public void setCategoria (CategoriaLivro novaCategoria) {
        this.categoria = novaCategoria;
    }

    /**
     * Modifica o ano de publicação do livro
     * @param anoDePublicacao novo ano de publicação do livro
     */
    public void setAnoDePublicacao (String anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    /**
     * Modifica a disponibilidade do livro
     * @param novaDisponibilidade nova disponibilidade do livro
     */
    public void setDisponibilidade (boolean novaDisponibilidade) {
        this.disponibilidade = novaDisponibilidade;
    }

    /**
     * Modifica a localização do livro
     * @param novaLocalizacao nova localização do livro
     */
    public void setLocalizacao (LocalizacaoLivro novaLocalizacao) {
        this.localizacao = novaLocalizacao;
    }

    /**
     * Modifica o ID do livro
     * @param livroID novo ID do livro
     */
    public void setLivroID(int livroID) {
        this.livroID = livroID;
    }

    /**
     * Método equals para os livros
     * @param l objeto livro
     * @return booleano em resposta a compração feita
     */
    public boolean equals(Livro l) {
        if ((l == null)|| getClass() != l.getClass()) {
            return false;
        }
        if (this == l) {
            return true;
        }
        return false;
    }

    /**
     * Método toString do livro para exibição de seus atributos
     * @return String contendo os atributos do livro
     */
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
