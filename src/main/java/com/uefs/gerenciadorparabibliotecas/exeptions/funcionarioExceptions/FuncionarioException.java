package com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions;

import com.uefs.gerenciadorparabibliotecas.model.Funcionario;

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
