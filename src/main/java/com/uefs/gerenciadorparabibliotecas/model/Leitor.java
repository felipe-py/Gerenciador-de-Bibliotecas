package com.uefs.gerenciadorparabibliotecas.model;

import java.util.List;
import java.util.ArrayList;

public class Leitor extends Pessoa {
    public int diasDeMulta;
    private List<Emprestimo> emprestimos;

    public void adicionarEmprestimo(Emprestimo emprestimo){
        this.emprestimos.add(emprestimo);
    }

    public Leitor(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, int novoUserID) {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
        this.diasDeMulta = 0;
        this.emprestimos = new ArrayList<>();
    }

    public int getDiasDeMulta() {
        return diasDeMulta;
    }
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setDiasDeMulta(int diasDeMulta) {
        this.diasDeMulta = diasDeMulta;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
