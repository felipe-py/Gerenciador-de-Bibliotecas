package com.uefs.gerenciadorparabibliotecas.dao.funcionario;

import com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions.FuncionarioException;
import com.uefs.gerenciadorparabibliotecas.model.Administrador;
import com.uefs.gerenciadorparabibliotecas.model.Bibliotecario;
import com.uefs.gerenciadorparabibliotecas.model.Funcionario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOFile implements FuncionarioDAO{
    private List<Funcionario> funcionarios;
    private final String filename;

    public FuncionarioDAOFile(String filename) {
        this.filename = filename;
        this.funcionarios = new ArrayList<>();
        this.funcionarios = ler();
    }

    public void salvar(List<Funcionario> funcionarios) {
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(this.filename));
            for (Funcionario funcionario : funcionarios) {
                file.writeObject(funcionario);
            }
            file.close();
        } catch (IOException e) {
        }
    }

    public List<Funcionario> ler() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            Funcionario funcionario = null;
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(this.filename));
            do {
                funcionario = (Funcionario) file.readObject();
                if (funcionario != null) {
                    funcionarios.add(funcionario);
                }
            } while (funcionario != null);
            file.close();
        } catch (IOException | ClassNotFoundException e) {
        }
        return funcionarios;
    }

    @Override
    public Funcionario criar(Funcionario funcionario) {
        if (funcionario instanceof Bibliotecario bibliotecario) {
        } else if (funcionario instanceof Administrador administrador) {
        }
        this.funcionarios.add(funcionario);
        salvar(this.funcionarios);
        return funcionario;
    }

    @Override
    public void deletar(Funcionario funcionario) throws FuncionarioException {
        boolean remove = this.funcionarios.remove(funcionario);
        if (!remove) {
            throw new FuncionarioException(FuncionarioException.DELETE, funcionario);
        }
        salvar(this.funcionarios);
    }

    @Override
    public void resetar() {
        this.funcionarios = new ArrayList<>();
        salvar(this.funcionarios);
    }

    @Override
    public Funcionario procurarPorID(Integer id) throws FuncionarioException{
        for(Funcionario funcionario: this.funcionarios){
            if(funcionario.getUserID().equals(id)){
                return funcionario;
            }
        }
        throw new FuncionarioException(FuncionarioException.SEARCH, id);
    }

    /**
     * Retorna todas os funcionários cadastradas no sistema
     * @return lista com funcionários
     */
    @Override
    public List<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    @Override
    public Funcionario atualizar(Funcionario obj) {
        int index = this.funcionarios.indexOf(obj);
        this.funcionarios.set(index, obj);
        salvar(this.funcionarios);
        return obj;
    }
}
