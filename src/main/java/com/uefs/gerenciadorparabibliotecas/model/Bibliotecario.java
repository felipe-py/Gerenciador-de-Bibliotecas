package com.uefs.gerenciadorparabibliotecas.model;

import com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions.FuncionarioException;

/**
 * Classe Model do bibliotecário
 */
public class Bibliotecario extends Funcionario {

    /**
     * Construtor da classe bibliotecário que reutiliza inteiramente o construtor de Funcionário
     * @param novoNome nome do bibliotecário
     * @param novoEndereco endereço do bibliotecário
     * @param novaSenha senha do bibliotecário
     * @param novoNumeroDeTelefone número de telefone do bibliotecário
     * @param novoUserID indentificação do bibliotecário
     */
    public Bibliotecario(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, Integer novoUserID) throws FuncionarioException {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
    }

    /**
     * Método toString do bibliotecário, herdado da classe Funcionário
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
