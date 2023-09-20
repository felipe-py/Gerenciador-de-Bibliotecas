package com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions;

import com.uefs.gerenciadorparabibliotecas.model.Leitor;

public class LeitorIDinvalido extends Exception{
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";
    private Leitor leitor;

    public LeitorIDinvalido(String create, Leitor leitor) {
        super(create);
        this.leitor = leitor;
    }
}
