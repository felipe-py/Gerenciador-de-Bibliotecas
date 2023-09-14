package com.uefs.gerenciadorparabibliotecas.model;

public abstract class Funcionario extends Pessoa {
    protected   CategoriaFuncionario    nivelDeProvilegio;

    public Funcionario(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, Integer novoUserID, CategoriaFuncionario novoNivelDePrivilegio) {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
        this.nivelDeProvilegio = novoNivelDePrivilegio;
    }
    public CategoriaFuncionario getNivelDeProvilegio () {
        return nivelDeProvilegio;
    }

    public void setNivelDeProvilegio(CategoriaFuncionario nivelDeProvilegio) {
        this.nivelDeProvilegio = nivelDeProvilegio;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
