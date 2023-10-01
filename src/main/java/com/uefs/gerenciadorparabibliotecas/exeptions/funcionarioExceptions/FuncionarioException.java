package com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions;
/**
 * GERENCIADOR DE BIBLIOTECA
 * @author Luis Felipe Cunha Silva
 * @author Lucas Lima Rodrigues
 * @version 1.0
 */
import com.uefs.gerenciadorparabibliotecas.model.Funcionario;

/**
 * Classe que reúne todas as exceções possíveis do funcionário
 * deletar, atualizar ou buscar funcionário
 */
public class FuncionarioException extends Exception{
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";
    public static final String SEARCH = "Operação de BUSCA não realizada.";
    private Funcionario funcionario;

    public FuncionarioException(String create, Funcionario funcionario) {
        super(create);
        this.funcionario = funcionario;
    }

    public FuncionarioException(String create, Integer id) {
        super(create + "ID inválido:" + id);
        this.funcionario = funcionario;
    }
}
