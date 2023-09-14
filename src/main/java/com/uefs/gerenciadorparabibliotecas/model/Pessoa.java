package com.uefs.gerenciadorparabibliotecas.model;


public abstract class Pessoa {
    protected   String    nome;
    protected   String    endereco;
    protected   String    senha;
    protected   String    numeroDeTelefone;
    protected   int   userID;



    //constructor
    public Pessoa (String novoNome, String novoEndereco, String novaSenha,
                   String novoNumeroDeTelefone, int novoUserID) {
        this.nome               = novoNome;
        this.endereco           = novoEndereco;
        this.senha              = novaSenha;
        this.numeroDeTelefone   = novoNumeroDeTelefone;
        this.userID             = novoUserID;

    }


    //getters
    public String getNome () { return nome; }
    public String getEndereco () { return endereco; }
    public String getSenha () { return senha; }
    public String getNumeroDeTelefone () { return numeroDeTelefone; }
    public int getUserID () { return userID; }

    //setters
    public void setNome (String novoNome) { this.nome = novoNome; }
    public void setEndereco (String novoEndereco) { this.endereco = novoEndereco; }
    public void setSenha (String novaSenha) { this.senha = novaSenha; }
    public void setNumeroDeTelefone(String numeroDeTelefone) { this.numeroDeTelefone = numeroDeTelefone; }
    public void setUserID(int userID) { this.userID = userID; }


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

    public boolean equals(Pessoa pessoa) {
        return super.equals(pessoa);
    }


}
