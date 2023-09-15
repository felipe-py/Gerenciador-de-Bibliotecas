package com.uefs.gerenciadorparabibliotecas.dao.funcionario;

import com.uefs.gerenciadorparabibliotecas.model.Administrador;
import com.uefs.gerenciadorparabibliotecas.model.Bibliotecario;
import com.uefs.gerenciadorparabibliotecas.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOList implements FuncionarioDAO{

    private List<Funcionario> funcionarios = new ArrayList<>();

    public FuncionarioDAOList() {
        this.funcionarios = funcionarios;
    }

    @Override
    public Funcionario criar(Funcionario funcionario) {
        if (funcionario instanceof Bibliotecario) {
            Bibliotecario bibliotecario = (Bibliotecario) funcionario;
        } else if (funcionario instanceof Administrador) {
            Administrador administrador = (Administrador) funcionario;
        }
        this.funcionarios.add(funcionario);
        return funcionario;
    }

    @Override
    public void deletar(int id) {
        for(Funcionario funcionario: this.funcionarios){
            if(funcionario.getUserID() == id){
                funcionarios.remove(funcionario);
            }
        }
    }

    @Override
    public void resetar() {
        this.funcionarios = new ArrayList<>();
    }

    @Override
    public Funcionario atualizar(Funcionario obj) {
        return null;
    }

    @Override
    public Funcionario procurarPorID(int id) {
        for(Funcionario funcionario: this.funcionarios){
            if(funcionario.getUserID() == id){
                return funcionario;
            }
        }
        return null;
    }

    @Override
    public List<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }
}
