package com.uefs.gerenciadorparabibliotecas.model;

public abstract class Funcionario extends Pessoa {

    public Funcionario(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, Integer novoUserID) {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
