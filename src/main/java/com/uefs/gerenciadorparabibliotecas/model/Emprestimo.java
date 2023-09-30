package com.uefs.gerenciadorparabibliotecas.model;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions.EmprestimoException;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private int emprestimoID;
    private LocalDate dataEmprestimo; // deve ser adicionada no momento em que o objeto for criado
    private LocalDate dataDevolucaoEsperada; // deve ser adicionada no momento em que o objeto for criado
    private int atraso;
    private Livro livroEmprestado;
    private Leitor mutuario;
    private Integer numeroDeRenovacoes;
    private boolean naoDevolvido;
    private LocalDate dataDevolvidoFinal;


    public Emprestimo (LocalDate novaDataEmprestimo, LocalDate novaDataDeDevolucao,
                       Livro novoLivroEmprestado, Leitor novoMutuario) {
        this.dataEmprestimo = novaDataEmprestimo;
        this.dataDevolucaoEsperada = novaDataDeDevolucao;
        this.atraso = 0;
        this.livroEmprestado = novoLivroEmprestado;
        this.mutuario = novoMutuario;
        this.numeroDeRenovacoes = 0;
        this.naoDevolvido = true;
    }

    public LocalDate getDataDevolvidoFinal() {
        return dataDevolvidoFinal;
    }

    public void setDataDevolvidoFinal(LocalDate dataDevolvidoFinal) {
        this.dataDevolvidoFinal = dataDevolvidoFinal;
    }

    public Emprestimo(Emprestimo emprestimo) {
    }

    public int getEmprestimoID() {
        return emprestimoID;
    }
    public LocalDate getDataEmprestimo () {
        return dataEmprestimo;
    }
    public LocalDate getdataDevolucaoEsperada () {
        return dataDevolucaoEsperada;
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
    public void setdataDevolucaoEsperada(LocalDate dataDevolucaoEsperada) { this.dataDevolucaoEsperada = dataDevolucaoEsperada; }
    public void setAtraso(int atraso) { this.atraso = atraso; }
    public void setLivroEmprestado(Livro livroEmprestado) { this.livroEmprestado = livroEmprestado; }
    public void setMutuario(Leitor mutuario) { this.mutuario = mutuario; }
    public void setNumeroDeRenovacoes(int numeroDeRenovacoes) { this.numeroDeRenovacoes = numeroDeRenovacoes; }
    public void setNaoDevolvido(boolean naoDevolvido) { this.naoDevolvido = naoDevolvido; }

    public void estenderEmprestimo (Emprestimo emprestimo) throws EmprestimoException {
        if (emprestimo.numeroDeRenovacoes < 1 && emprestimo.getLivroEmprestado().naoEstaReservado()) {
            numeroDeRenovacoes++;
            emprestimo.dataDevolucaoEsperada = dataDevolucaoEsperada.plus(7, ChronoUnit.DAYS);
            MasterDAO.getEmprestimoDAO().atualizar(emprestimo);
        }
        else {
            throw new EmprestimoException(EmprestimoException.RENEW, emprestimo);
        }
    }

    public int calcularAtraso (Emprestimo emprestimo, LocalDate data) {
        int diferenca = (int) ChronoUnit.DAYS.between(data, emprestimo.dataDevolucaoEsperada);
        if (diferenca > 0) {
            return 0;
        } else {
            return diferenca;
        }
    }

    /*public int calcularMulta (Emprestimo emprestimo, LocalDate data) {
        int atraso = emprestimo.calcularAtraso(emprestimo, data);
        int multa  = 2*(atraso);
        return multa;
    }
    public void aplicarMulta (Emprestimo emprestimo, LocalDate data) {
        int multa = this.calcularMulta(emprestimo, data);
        if (multa > 0) {
            this.mutuario.setDiasDeMulta(multa);
        }
    }*/

    public void finalizarEmprestimo (Emprestimo emprestimo, LocalDate data) {
        this.livroEmprestado.setDisponibilidade(true);
        this.setNaoDevolvido(false);
        this.setAtraso(this.calcularAtraso(emprestimo, data));
        this.getMutuario().setDiasDeMulta((this.calcularAtraso(emprestimo, data) * 2) * -1);
        this.setDataDevolvidoFinal(LocalDate.now());

        MasterDAO.getEmprestimoDAO().atualizar(emprestimo);
        MasterDAO.getLivroDAO().atualizar(emprestimo.getLivroEmprestado());
        MasterDAO.getLeitorDAO().atualizar(emprestimo.getMutuario());

    }

    //Método para verificar se o emprestimo pode ser feito
    public boolean verificacao (Emprestimo emprestimo) throws Exception {
        if (!emprestimo.getLivroEmprestado().estaReservado()) {
            throw new EmprestimoException(EmprestimoException.RENEW, emprestimo);           //criar exception para livro não disponivel
        }
        if (emprestimo.getMutuario().getDiasDeMulta() > 0) {
            throw new LeitorException(LeitorException.USERFINED, emprestimo.getMutuario());           //criar exception para usuario multado
        }
        if (emprestimo.mutuario.isLeitorBanido()) {    //criar atributo bool para verificar se esta banido
            throw new LeitorException(LeitorException.BAN, emprestimo.getMutuario());         //criar exception para usuario banido
        }
        return true;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "emprestimoID=" + emprestimoID +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucaoEsperada=" + dataDevolucaoEsperada +
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
