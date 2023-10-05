package com.uefs.gerenciadorparabibliotecas.model;

/**
 * Classe Model do funcionário
 */
public abstract class Funcionario extends Pessoa {

    /**
     * Construtor da classe funcionário reutilizando todos os itens da classe Pessoa
     * @param novoNome nome do funionário
     * @param novoEndereco endereço do funcionário
     * @param novaSenha senha do funcionário
     * @param novoNumeroDeTelefone número de telefone do funcionário
     * @param novoUserID indentificação do funcionário
     */
    public Funcionario(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, Integer novoUserID) {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
    }

    /**
     * Método toString do funcionário, herdado da classe Pessoa
     * @return String do funcionário
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
