package com.uefs.gerenciadorparabibliotecas.dao.emprestimo;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions.EmprestimoException;
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
        emprestimo.getMutuario().adicionarEmprestimo(emprestimo);
        emprestimo.getLivroEmprestado().setDisponibilidade(false);
        return emprestimo;
    }

    @Override
    public void deletar(Emprestimo emprestimo) throws EmprestimoException {
        boolean remove = this.emprestimos.remove(emprestimo);
        if (!remove) {
            throw new EmprestimoException(EmprestimoException.DELETE, emprestimo);
        }
    }
    @Override
    public void resetar() {
        this.emprestimos = new ArrayList<>();
        this.novoID = 0;
    }

    @Override
    public Emprestimo procurarPorID(Integer id) throws EmprestimoException{
        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.getEmprestimoID() == id) {
                return emprestimo;
            }
        }
        throw new EmprestimoException(EmprestimoException.SEARCH, id);
    }
}
