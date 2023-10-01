package com.uefs.gerenciadorparabibliotecas.model;

public abstract class Funcionario extends Pessoa {

    /**
     * Construtor da classe funcionário reutilizando todos os itens da classe pessoa
     * @param novoNome
     * @param novoEndereco
     * @param novaSenha
     * @param novoNumeroDeTelefone
     * @param novoUserID
     */
    public Funcionario(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, Integer novoUserID) {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
