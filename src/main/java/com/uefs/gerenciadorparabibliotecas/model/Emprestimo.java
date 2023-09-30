package com.uefs.gerenciadorparabibliotecas.model;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions.EmprestimoException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private int emprestimoID;
    private LocalDate dataEmprestimo; // deve ser adicionada no momento em que o objeto for criado
    private LocalDate dataDevolucao; // deve ser adicionada no momento em que o objeto for criado
    private int atraso;
    private Livro livroEmprestado;
    private Leitor mutuario;
    private Integer numeroDeRenovacoes;
    private boolean naoDevolvido;


    public Emprestimo (LocalDate novaDataEmprestimo, LocalDate novaDataDeDevolucao,
                       Livro novoLivroEmprestado, Leitor novoMutuario) {
        this.dataEmprestimo = novaDataEmprestimo;
        this.dataDevolucao = novaDataDeDevolucao;
        this.atraso = 0;
        this.livroEmprestado = novoLivroEmprestado;
        this.mutuario = novoMutuario;
        this.numeroDeRenovacoes = 0;
        this.naoDevolvido = true;
    }

    public int getEmprestimoID() {
        return emprestimoID;
    }
    public LocalDate getDataEmprestimo () {
        return dataEmprestimo;
    }
    public LocalDate getDataDevolucao () {
        return dataDevolucao;
    }
    public int getAtraso () {
        return atraso;
    }
    public Livro getLivroEmprestado () {
        return livroEmprestado;
    }
    public Leitor getMutuario () {
        return mutuario;
    }
    public int getNumeroDeRenovacoes () {
        return numeroDeRenovacoes;
    }
    public boolean getNaoDevolvido () { return naoDevolvido; }

    public void setEmprestimoID(int emprestimoID) { this.emprestimoID = emprestimoID; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }
    public void setAtraso(int atraso) { this.atraso = atraso; }
    public void setLivroEmprestado(Livro livroEmprestado) { this.livroEmprestado = livroEmprestado; }
    public void setMutuario(Leitor mutuario) { this.mutuario = mutuario; }
    public void setNumeroDeRenovacoes(int numeroDeRenovacoes) { this.numeroDeRenovacoes = numeroDeRenovacoes; }
    public void setNaoDevolvido(boolean naoDevolvido) { this.naoDevolvido = naoDevolvido; }

    public void estenderEmprestimo (Emprestimo emprestimo) throws EmprestimoException {
        if (emprestimo.numeroDeRenovacoes < 1 && emprestimo.getLivroEmprestado().naoEstaReservado()) {
            numeroDeRenovacoes++;
            emprestimo.dataDevolucao = dataDevolucao.plus(7, ChronoUnit.DAYS);
            MasterDAO.getEmprestimoDAO().atualizar(emprestimo);
        }
        else {
            throw new EmprestimoException(EmprestimoException.RENEW, emprestimo);
        }
    }

    public void calcularAtraso (Emprestimo emprestimo, LocalDate data) {
        int diferenca = (int) ChronoUnit.DAYS.between(data, emprestimo.dataDevolucao);
        if (diferenca > 0) {
            emprestimo.atraso = 0;
        } else {
            emprestimo.atraso = diferenca;
        }
        MasterDAO.getEmprestimoDAO().atualizar(emprestimo);
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "emprestimoID=" + emprestimoID +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", atraso=" + atraso +
                ", livroEmprestado=" + livroEmprestado +
                ", mutuario=" + mutuario +
                ", numeroDeRenovacoes=" + numeroDeRenovacoes +
                ", naoDevolvido=" + naoDevolvido +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
