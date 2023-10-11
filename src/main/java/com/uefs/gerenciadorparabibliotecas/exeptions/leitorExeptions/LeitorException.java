package com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions;
/**
 * GERENCIADOR DE BIBLIOTECA
 * @author Luis Felipe Cunha Silva
 * @author Lucas Lima Rodrigues
 * @version 1.0
 */
import com.uefs.gerenciadorparabibliotecas.model.Leitor;

/**
 * Classe que reúne todas as exceções possíveis do leitor
 * deletar, atualizar, buscar, multado ou banido
 */

public class LeitorException extends Exception{
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";
    public static final String SEARCH = "Operação de BUSCA não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";
    public static final String BAN = "Usuario bloqueado";
    public static final String INVALID_INFO = "ERRO: ";
    public static final String EMPITY_INFO = "INFORMAÇÂO VAZIA";
    private Leitor leitor;

    public LeitorException(String create, Leitor leitor) {
        super(create);
        this.leitor = leitor;
    }

    public LeitorException(String create, Integer id) {
        super(create + "ID inválido:" + id);
    }

    public LeitorException(String create, String atributo) {
        super(create + atributo + " INVÁLIDO/EXISTENTE");
    }
}
