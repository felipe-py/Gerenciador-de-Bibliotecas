package com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions;

import com.uefs.gerenciadorparabibliotecas.model.Emprestimo;
import com.uefs.gerenciadorparabibliotecas.model.Livro;

public class EmprestimoException extends Exception{
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";
    private Emprestimo emprestimo;

    public EmprestimoException(String create, Emprestimo emprestimo) {
        super(create);
        this.emprestimo = emprestimo;
    }
}
