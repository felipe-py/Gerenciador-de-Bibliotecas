package com.uefs.gerenciadorparabibliotecas.model;

public class Bibliotecario extends Funcionario {

    public Bibliotecario(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, Integer novoUserID) {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
