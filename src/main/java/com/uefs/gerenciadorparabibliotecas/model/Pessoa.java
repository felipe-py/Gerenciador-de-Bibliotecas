package com.uefs.gerenciadorparabibliotecas.model;

import java.io.Serializable;

/**
 * Classe Model da Pessoa (Classe mãe dos usuários)
 */
public abstract class Pessoa implements Serializable {
    /**
     * nome: String que armazena o nome dos usuários do sistema
     * endereco: String que armazena o endereço dos usuários do sistema
     * senha: String que armazena a senha dos usuários do sistema
     * numeroDeTelefone: String que armazena o número de telefone dos usuários do sistema
     * userID: String que armazena a indentificação dos usuários do sistema
     */
    private   String    nome;
    private   String    endereco;
    private   String    senha;
    private   String    numeroDeTelefone;
    private   Integer   userID;

    /**
     * Construtor de Pessoa, utilizado para leitores e funcionários que serão cadastrados no sistema
     * @param novoNome nome do usuário
     * @param novoEndereco endereço do usuário
     * @param novaSenha senha do usuário
     * @param novoNumeroDeTelefone número de telefone do usuário
     * @param novoUserID indentificação do usuário
     */
    public Pessoa (String novoNome, String novoEndereco, String novaSenha,
                   String novoNumeroDeTelefone, Integer novoUserID) {
        this.nome               = novoNome;
        this.endereco           = novoEndereco;
        this.senha              = novaSenha;
        this.numeroDeTelefone   = novoNumeroDeTelefone;
        this.userID             = novoUserID;

    }

    /**
     * Acessa o nome do usuário
     * @return nome do usuário
     */
    public String getNome () { return nome; }

    /**
     * Acessa o endereço do usuário
     * @return endereço do usuário
     */
    public String getEndereco () { return endereco; }

    /**
     * Acessa a senha do usuário
     * @return senha do usuário
     */
    public String getSenha () { return senha; }

    /**
     * Acessa o número de telefone do usuário
     * @return número de telefone do usuário
     */
    public String getNumeroDeTelefone () { return numeroDeTelefone; }

    /**
     * Acessa o ID do usuário
     * @return ID do usuário
     */
    public Integer getUserID () { return userID; }

    /**
     * Modifica o nome do usuário
     * @param novoNome novo nome do usuário
     */
    public void setNome (String novoNome) { this.nome = novoNome; }

    /**
     * Modifica o endereço do usuário
     * @param novoEndereco novo nome do usuário
     */
    public void setEndereco (String novoEndereco) { this.endereco = novoEndereco; }

    /**
     * Modifica a senha do usuário
     * @param novaSenha nova senha do usuário
     */
    public void setSenha (String novaSenha) { this.senha = novaSenha; }

    /**
     * Modifica o número de telefone do usuário
     * @param numeroDeTelefone novo nome do usuário
     */
    public void setNumeroDeTelefone(String numeroDeTelefone) { this.numeroDeTelefone = numeroDeTelefone; }

    /**
     * Modifica o ID do usuário
     * @param userID novo nome do usuário
     */
    public void setUserID(Integer userID) { this.userID = userID; }

    /**
     * toString para os usuários do sistema e seus atributos
     * @return string com os atributos do usuário
     */
    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", senha='" + senha + '\'' +
                ", numeroDeTelefone='" + numeroDeTelefone + '\'' +
                ", userID=" + userID +
                '}';
    }

    /**
     * Método equals para os usuários do sistema
     * @param pessoa objeto pessoa
     * @return equals do objeto
     */
    public boolean equals(Pessoa pessoa) {
        return super.equals(pessoa);
    }
}
