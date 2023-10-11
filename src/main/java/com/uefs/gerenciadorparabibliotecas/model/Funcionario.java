package com.uefs.gerenciadorparabibliotecas.model;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions.FuncionarioException;

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
     * O método de validação do funcionário é chamado para averiguar os atributos enviados
     */
    public Funcionario(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, Integer novoUserID) throws FuncionarioException {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
        validacaoFuncionario();
    }

    /**
     * Método que realiza a validação dos atributos do funcionário, é validado se os atributos enviados são valores
     * vazios, ID enviado já esteja cadastrado no sistema, nome possua um número em sua formação e número de telefone
     * com letra em sua formação
     * @throws FuncionarioException caso qualquer uma das validações não sejam correspondidas
     */
    private void validacaoFuncionario() throws FuncionarioException {
        if(!(this.getNome().matches("^[a-zA-Z\\s]*$"))) {
            throw new FuncionarioException(FuncionarioException.INVALID_INFO, this.getNome());
        }
        if(!(this.getNumeroDeTelefone().matches("^[0-9\\s-]*$"))){
            throw new FuncionarioException(FuncionarioException.INVALID_INFO, this.getNumeroDeTelefone());
        }
        if(MasterDAO.getFuncionarioDAO().getFuncionarios().contains(getUserID())){
            throw new FuncionarioException(FuncionarioException.INVALID_INFO, this.getUserID());
        }
        if(this.getNome().isEmpty() || this.getNumeroDeTelefone().isEmpty() || this.getSenha().isEmpty() ||
                this.getEndereco().isEmpty()){
            throw new FuncionarioException(FuncionarioException.EMPITY_INFO, this);
        }
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
