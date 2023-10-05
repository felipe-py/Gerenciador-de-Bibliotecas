package com.uefs.gerenciadorparabibliotecas.model;

/**
 * Classe Model do administrador
 */
public class Administrador extends Funcionario{

    /**
     * Construtor da classe Administrador que reutiliza inteiramente o construtor de Funcionário
     * @param novoNome nome do administrador
     * @param novoEndereco endereço do administrador
     * @param novaSenha senha do administrador
     * @param novoNumeroDeTelefone número de telefone do administrador
     * @param novoUserID indentificação do administrador
     */
    public Administrador(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, Integer novoUserID) {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
    }

    /**
     * Método toString do administrador, herdado da classe Funcionário
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
