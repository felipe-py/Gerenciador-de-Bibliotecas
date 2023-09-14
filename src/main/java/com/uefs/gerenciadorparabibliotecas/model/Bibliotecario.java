package com.uefs.gerenciadorparabibliotecas.model;

public class Bibliotecario extends Funcionario {

    public Bibliotecario(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, Integer novoUserID, CategoriaFuncionario novoNivelDePrivilegio) {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID, novoNivelDePrivilegio);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
