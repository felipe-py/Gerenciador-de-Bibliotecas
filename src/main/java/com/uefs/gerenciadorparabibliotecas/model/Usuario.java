package com.uefs.gerenciadorparabibliotecas.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa{
    private Integer diasDeMulta;
    private List<Emprestimo> emprestimos;

    //constructor
    public Usuario(String novoNome, String novoEndereco, String novaSenha, CategoriaFuncionario novoNivelDeProvilegio, String novoNumeroDeTelefone, Integer novoUserID) {
        super(novoNome, novoEndereco, novaSenha, novoNivelDeProvilegio, novoNumeroDeTelefone, novoUserID);
        this.diasDeMulta = 0;
        this.emprestimos = new ArrayList<>();
    }

    public Integer getDiasDeMulta () { return diasDeMulta; }
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setDiasDeMulta(Integer diasDeMulta) { this.diasDeMulta = diasDeMulta; }
    public void setEmprestimos(List<Emprestimo> emprestimos) { this.emprestimos = emprestimos; }

    @Override
    public String toString() {
        return "Usuario{" +
                "diasDeMulta=" + diasDeMulta +
                ", emprestimos=" + emprestimos +
                '}';
    }
}