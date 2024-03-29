package com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions;
/**
 * GERENCIADOR DE BIBLIOTECA
 * @author Luis Felipe Cunha Silva
 * @author Lucas Lima Rodrigues
 * @version 1.0
 */
import com.uefs.gerenciadorparabibliotecas.model.Livro;

/**
 * Classe que reúne todas as exceções possíveis do livro
 * deletar, atualizar ou buscar livro
 */
public class LivroException extends Exception{
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";
    public static final String SEARCH = "Operação de BUSCA não realizada. (INFORMAÇÃO ENVIADA INVÁLIDA/INEXISTENTE)";
    public static final String INVALID_INFO = "ERRO: ";
    public static final String EMPITY_INFO = "INFORMAÇÂO VAZIA";
    private Livro livro;

    public LivroException(String create, Livro livro) {
        super(create);
        this.livro = livro;
    }

    public LivroException(String create) {
        super(create);
    }

    public LivroException(String create, String atributo) {
        super(create + atributo + " TERMO INVÁLIDO");
    }
}
