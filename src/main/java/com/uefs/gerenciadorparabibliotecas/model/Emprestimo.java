package com.uefs.gerenciadorparabibliotecas.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Emprestimo {
    protected Integer emprestimoID;
    protected LocalDate dataEmprestimo; // deve ser adicionada no momento em que o objeto for criado
    protected LocalDate dataDevolucao; // deve ser adicionada no momento em que o objeto for criado
    protected Integer atraso;
    protected Livro livroEmprestado;
    protected Usuario mutuario;
    protected Integer numeroDeRenovacoes;
    protected boolean naoDevolvido;


    public Emprestimo (Integer novoEmprestimoID, LocalDate novaDataEmprestimo, LocalDate novaDataDeDevolucao,
                       Livro novoLivroEmprestado, Usuario novoMutuario) {
        this.emprestimoID = novoEmprestimoID;
        this.dataEmprestimo = novaDataEmprestimo;
        this.dataDevolucao = novaDataDeDevolucao;
        this.atraso = 0;
        this.livroEmprestado = novoLivroEmprestado;
        this.mutuario = novoMutuario;
        this.numeroDeRenovacoes = 0;
        this.naoDevolvido = true;

    }

    public Integer getEmprestimoID() {
        return emprestimoID;
    }
    public LocalDate getDataEmprestimo () {
        return dataEmprestimo;
    }
    public LocalDate getDataDevolucao () {
        return dataDevolucao;
    }
    public Integer getAtraso () {
        return atraso;
    }
    public Livro getLivroEmprestado () {
        return livroEmprestado;
    }
    public Usuario getMutuario () {
        return mutuario;
    }
    public Integer getNumeroDeRenovacoes () {
        return numeroDeRenovacoes;
    }
    public boolean getNaoDevolvido () { return naoDevolvido; }

    public void setEmprestimoID(Integer emprestimoID) { this.emprestimoID = emprestimoID; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }
    public void setAtraso(Integer atraso) { this.atraso = atraso; }
    public void setLivroEmprestado(Livro livroEmprestado) { this.livroEmprestado = livroEmprestado; }
    public void setMutuario(Usuario mutuario) { this.mutuario = mutuario; }
    public void setNumeroDeRenovacoes(Integer numeroDeRenovacoes) { this.numeroDeRenovacoes = numeroDeRenovacoes; }
    public void setNaoDevolvido(boolean naoDevolvido) { this.naoDevolvido = naoDevolvido; }

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

    /*

    public void registrarEmprestimo (Usuario userID, Livro livroID) {
        Integer contador = 0;
        while (Emprestimo.mapaEmprestimos.containsKey(contador)) {
            contador++;
        }
        Emprestimo.mapaEmprestimos.put(contador, this);
        this.emprestimoID = contador;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plus(Period.ofWeeks(1));
        this.mutuario = userID;
        this.livroEmprestado = livroID;
    }
    public void extenderPrazo () {
        if (numeroDeRenovacoes <1) {
            numeroDeRenovacoes++;
            this.dataDevolucao = dataDevolucao.plus(Period.ofWeeks(1));
            return;
        }
        return;
    }
    public Integer calcularAtraso () {
        if ((ChronoUnit.DAYS.between(LocalDate.now(), dataDevolucao) > 0) && naoDevolvido) {
            this.atraso = (int) ChronoUnit.DAYS.between(LocalDate.now(), dataDevolucao);
            return this.atraso;
        }
        return 0;
    }
    public void devolverLivro () {
        this.naoDevolvido = false;
    }

    /*protected void definirIDEmprestimo (Emprestimo emprestimoNovo) {
        Integer contador = 0;
        while (Emprestimo.mapaEmprestimos.containsKey(contador)) {
            contador++;
        }
        Emprestimo.mapaEmprestimos.put(contador, emprestimoNovo);
        this.emprestimoID = contador;
    }

    protected void calcularAtraso () {
        int diferenca = (int) ChronoUnit.DAYS.between(LocalDate.now(), dataDevolucao);
        if (diferenca > 0) {
            this.atraso = diferenca;
        }
        else {
            this.atraso = 0;
        }

    }

    protected void definirLivro () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o código do livro emprestado (não insira o ISBN!) : \n");
        //int  codigo = scanner.nextLine();
        // encontrar um livro a partir do mapa de livros.
        //if 'codigo não esta mapeado' tentar inserir novamente.
        //this.livroEmprestado = livro;
    }

    protected void definirMutuario () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o código do mutuário: \n");
        //int  codigo = scanner.nextLine();
        // encontrar o mutuario a partir do mapa de Funcionarios.
        //if 'codigo não esta mapeado' tentar inserir novamente.
        //this.mutuario = mutuario;
    }

    protected void estenderEmprestimo () {
        if (this.numeroDeRenovacoes < 1) {
            numeroDeRenovacoes++;
            this.dataDevolucao = dataDevolucao.plus(7, ChronoUnit.DAYS);
        }
        else {
            System.out.print("O prazo ja foi estendido até o limite.\n");
        }
    }*/
}
