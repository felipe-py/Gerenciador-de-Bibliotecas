package com.uefs.gerenciadorparabibliotecas.dao.emprestimo;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions.EmprestimoException;
import com.uefs.gerenciadorparabibliotecas.model.Emprestimo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAOFile implements EmprestimoDAO{
    private String filename;
    private List<Emprestimo> emprestimos;
    private int novoID;

    public EmprestimoDAOFile(String filename) {
        this.filename = filename;
        this.emprestimos = new ArrayList<>();
        this.emprestimos = ler();
        if(this.emprestimos.isEmpty()){
            this.novoID = 0;
        }
        else{
            this.novoID = this.emprestimos.get(this.emprestimos.size() - 1).getEmprestimoID() + 1;
        }
    }

    public void salvar(List<Emprestimo> emprestimos) {
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(this.filename));
            for (Emprestimo emprestimo : emprestimos) {
                file.writeObject(emprestimo);
            }
            file.close();
        } catch (IOException e) {
        }
    }

    public List<Emprestimo> ler() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        try {
            Emprestimo emprestimo = null;
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(this.filename));
            do {
                emprestimo = (Emprestimo) file.readObject();
                if (emprestimo != null) {
                    emprestimos.add(emprestimo);
                }
            } while (emprestimo != null);
            file.close();
        } catch (IOException | ClassNotFoundException e) {
        }
        return emprestimos;
    }

    /**
     * Retorna todas os empréstimos cadastradas no sistema
     * @return lista com empréstimos
     */
    @Override
    public List<Emprestimo> getEmprestimos(){
        return emprestimos;
    }

    @Override
    public Emprestimo criar(Emprestimo emprestimo){
        emprestimo.setEmprestimoID(this.novoID);
        this.novoID++;
        this.emprestimos.add(emprestimo);

        emprestimo.getMutuario().adicionarEmprestimo(emprestimo);  // Adiciona empréstimo a histórico do leitor
        emprestimo.getLivroEmprestado().setDisponibilidade(false); // Atualiza disponibilidade do livro

        emprestimo.getLivroEmprestado().setNumeroEmprestimos();  // Atualiza a quantidade empréstimos do livro

        MasterDAO.getLivroDAO().atualizar(emprestimo.getLivroEmprestado());
        MasterDAO.getLeitorDAO().atualizar(emprestimo.getMutuario());
        salvar(this.emprestimos);
        return emprestimo;
    }

    @Override
    public void deletar(Emprestimo emprestimo) throws EmprestimoException {
        boolean remove = this.emprestimos.remove(emprestimo);
        if (!remove) {
            throw new EmprestimoException(EmprestimoException.DELETE, emprestimo);
        }
        salvar(this.emprestimos);
    }
    @Override
    public void resetar() {
        this.emprestimos = new ArrayList<>();
        this.novoID = 0;
        salvar(this.emprestimos);
    }

    @Override
    public Emprestimo atualizar(Emprestimo emprestimo) {
        int emprestimoID = emprestimo.getEmprestimoID();

        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo livroNaLista = emprestimos.get(i);
            if (livroNaLista.getEmprestimoID() == emprestimoID) {
                emprestimos.set(i, emprestimo);
                salvar(this.emprestimos);
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

    /**
     * Método para calcular todos os livros que estão em atraso no sistema
     * @param data para cálcular empréstimos atrasados
     * @return soma dos livros que estão em atraso no empréstimo
     */
    @Override
    public Integer nLivrosatrasados(LocalDate data) {
        Integer soma = 0;
        List<Emprestimo> emprestimosRealizados = MasterDAO.getEmprestimoDAO().getEmprestimos();
        for(Emprestimo emprestimo: emprestimosRealizados){
            if(emprestimo.calcularAtraso(emprestimo, data) < 0){
                soma += 1;
            }
        }
        return soma;
    }
}
