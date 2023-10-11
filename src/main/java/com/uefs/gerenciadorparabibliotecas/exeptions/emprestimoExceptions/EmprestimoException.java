package com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions;
/**
 * GERENCIADOR DE BIBLIOTECA
 * @author Luis Felipe Cunha Silva
 * @author Lucas Lima Rodrigues
 * @version 1.0
 */
import com.uefs.gerenciadorparabibliotecas.model.Emprestimo;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;

/**
 * Classe para reunir todas as exceções relacionadas ao empréstimo
 * Deletar, atualizar, buscar e renovar empréstimo
 */
public class EmprestimoException extends Exception{
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";
    public static final String SEARCH = "Operação de BUSCA não realizada.";
    public static final String RENEW = "Operação de RENOVAÇÃO não realizada";
    public static final String USERFINED = "Usuário multado";
    private Leitor leitor;

    private Emprestimo emprestimo;

    public EmprestimoException(String create, Emprestimo emprestimo) {
        super(create);
        this.emprestimo = emprestimo;
    }

    public EmprestimoException(String create, Leitor leitor) {
        super(create);
        this.leitor = leitor;
    }

    public EmprestimoException(String create, Integer id) {
        super(create + "ID inválido:" + id);
        this.emprestimo = emprestimo;
    }
}
