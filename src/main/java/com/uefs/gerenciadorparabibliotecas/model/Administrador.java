package com.uefs.gerenciadorparabibliotecas.model;

public class Administrador extends Funcionario{


    public Administrador(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, Integer novoUserID) {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
