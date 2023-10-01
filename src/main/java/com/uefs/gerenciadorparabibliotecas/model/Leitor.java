package com.uefs.gerenciadorparabibliotecas.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Leitor extends Pessoa {
    private int diasDeMulta;
    private LocalDate dataDoFimDaMulta;

    public boolean isLeitorBanido() {
        return leitorBanido;
    }

    public void setLeitorBanido(boolean leitorBanido) {
        this.leitorBanido = leitorBanido;
    }

    private boolean leitorBanido = false;
    private List<Emprestimo> historicoEmprestimos;

    public void adicionarEmprestimo(Emprestimo emprestimo){
        this.historicoEmprestimos.add(emprestimo);
    }

    public Leitor(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, int novoUserID) {
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
        this.diasDeMulta = 0;
        this.historicoEmprestimos = new ArrayList<>();
        this.dataDoFimDaMulta = LocalDate.now();
    }

    public LocalDate getDataDoFimDaMulta() {
        return dataDoFimDaMulta;
    }

    public void setDataDoFimDaMulta(LocalDate dataDoFimDaMulta) {
        this.dataDoFimDaMulta = dataDoFimDaMulta;
    }

    public int getDiasDeMulta() {
        return diasDeMulta;
    }
    public List<Emprestimo> getEmprestimos() {
        return historicoEmprestimos;
    }

    public void setDiasDeMulta(int diasDeMulta) {
        this.diasDeMulta = diasDeMulta;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.historicoEmprestimos = emprestimos;
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
