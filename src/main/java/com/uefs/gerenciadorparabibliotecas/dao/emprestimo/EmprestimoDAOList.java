package com.uefs.gerenciadorparabibliotecas.dao.emprestimo;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions.EmprestimoException;
import com.uefs.gerenciadorparabibliotecas.model.Emprestimo;

import java.time.LocalDate;
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
    public Emprestimo criar(Emprestimo emprestimo){
        if(emprestimo.getMutuario().getDiasDeMulta() != 0 || emprestimo.getMutuario().isLeitorBanido()){
            return null;
        }
        emprestimo.setEmprestimoID(this.novoID);
        this.novoID++;
        this.emprestimos.add(emprestimo);
        emprestimo.getMutuario().adicionarEmprestimo(emprestimo);
        emprestimo.getLivroEmprestado().setDisponibilidade(false);
        MasterDAO.getLivroDAO().atualizar(emprestimo.getLivroEmprestado());
        MasterDAO.getLeitorDAO().atualizar(emprestimo.getMutuario());
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
    public Emprestimo atualizar(Emprestimo emprestimo) {
        int emprestimoID = emprestimo.getEmprestimoID();

        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo livroNaLista = emprestimos.get(i);
            if (livroNaLista.getEmprestimoID() == emprestimoID) {
                emprestimos.set(i, emprestimo);
                return emprestimo;
            }
        }
        return null;
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
