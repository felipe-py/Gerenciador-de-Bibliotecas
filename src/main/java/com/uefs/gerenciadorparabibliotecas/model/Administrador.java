package com.uefs.gerenciadorparabibliotecas.model;

public class Administrador extends Funcionario{

    /**
     * Construtor da classe bibliotecário que reutiliza inteiramente o construtor de Funcionário
     * @param novoNome
     * @param novoEndereco
     * @param novaSenha
     * @param novoNumeroDeTelefone
     * @param novoUserID
     */
    public Administrador(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, Integer novoUserID) {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
