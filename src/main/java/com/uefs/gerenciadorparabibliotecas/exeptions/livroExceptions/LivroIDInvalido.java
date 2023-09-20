package com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions;

import com.uefs.gerenciadorparabibliotecas.model.Livro;

public class LivroIDInvalido extends Exception{
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";
    private Livro livro;

    public LivroIDInvalido(String create, Livro livro) {
        super(create);
        this.livro = livro;
    }
}