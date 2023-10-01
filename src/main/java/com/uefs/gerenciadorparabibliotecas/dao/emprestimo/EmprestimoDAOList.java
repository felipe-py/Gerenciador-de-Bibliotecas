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
        if(emprestimo.getMutuario().getDataDoFimDaMulta().isAfter(emprestimo.getDataEmprestimo())){
            return null;
        }
        emprestimo.setEmprestimoID(this.novoID);
        this.novoID++;
        this.emprestimos.add(emprestimo);

        emprestimo.getMutuario().adicionarEmprestimo(emprestimo);  // Adiciona empréstimo a histórico do leitor
        emprestimo.getLivroEmprestado().setDisponibilidade(false); // Atualiza disponibilidade do livro

        emprestimo.getLivroEmprestado().setNumeroEmprestimos();  // Atualiza a quantidade empréstimos do livro

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
