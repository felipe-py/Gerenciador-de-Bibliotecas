package com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions;
/**
 * GERENCIADOR DE BIBLIOTECA
 * @author Luis Felipe Cunha Silva
 * @author Lucas Lima Rodrigues
 * @version 1.0
 */
import com.uefs.gerenciadorparabibliotecas.model.Emprestimo;

/**
 * Classe para reunir todas as exceções relacionadas ao empréstimo
 * Deletar, atualizar, buscar e renovar empréstimo
 */
public class EmprestimoException extends Exception{
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";
    public static final String SEARCH = "Operação de BUSCA não realizada.";
    public static final String RENEW = "Operação de RENOVAÇÃO não realizada";
    private Emprestimo emprestimo;

    public EmprestimoException(String create, Emprestimo emprestimo) {
        super(create);
        this.emprestimo = emprestimo;
    }

    public EmprestimoException(String create, Integer id) {
        super(create + "ID inválido:" + id);
        this.emprestimo = emprestimo;
    }
}
