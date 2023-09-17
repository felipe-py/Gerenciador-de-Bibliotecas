package com.uefs.gerenciadorparabibliotecas.dao.emprestimo;

import com.uefs.gerenciadorparabibliotecas.model.Emprestimo;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAOList implements EmprestimoDAO{

    private List<Emprestimo> emprestimos = new ArrayList<>();
    private int novoID = 0;

    public EmprestimoDAOList() {
        this.emprestimos = emprestimos;
        this.novoID = novoID;
    }

    @Override
    public List<Emprestimo> getEmprestimos(){
        return emprestimos;
    }

    @Override
    public Emprestimo criar(Emprestimo emprestimo) {
        emprestimo.setEmprestimoID(this.novoID);
        this.novoID++;
        this.emprestimos.add(emprestimo);
        return emprestimo;
    }

    @Override
    public void deletar(int id) {
        for(Emprestimo livro: this.emprestimos){
            if(livro.getEmprestimoID() == id){
                this.emprestimos.remove(livro);
            }
        }
    }
    @Override
    public void resetar() {
        this.emprestimos = new ArrayList<>();
        this.novoID = 0;
    }




    @Override
    public Emprestimo procurarPorID(int id) {
        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.getEmprestimoID() == id) {
                return emprestimo;
            }
        }
        return null;
    }
}
