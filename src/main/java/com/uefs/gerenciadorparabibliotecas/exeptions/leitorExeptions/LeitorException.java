package com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions;

import com.uefs.gerenciadorparabibliotecas.model.Leitor;

public class LeitorException extends Exception{
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";
    public static final String SEARCH = "Operação de BUSCA não realizada.";
    private Leitor leitor;

    public LeitorException(String create, Leitor leitor) {
        super(create);
        this.leitor = leitor;
    }
}