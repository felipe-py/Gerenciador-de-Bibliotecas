package com.uefs.gerenciadorparabibliotecas.dao.funcionario;

import com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions.FuncionarioException;
import com.uefs.gerenciadorparabibliotecas.model.Administrador;
import com.uefs.gerenciadorparabibliotecas.model.Bibliotecario;
import com.uefs.gerenciadorparabibliotecas.model.Funcionario;

import java.util.ArrayList;
import java.util.List;
/**
 * CRUD e acesso aos dados dos funcionários
 */
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
    public void deletar(Funcionario funcionario) throws FuncionarioException {
        boolean remove = this.funcionarios.remove(funcionario);
        if (!remove) {
            throw new FuncionarioException(FuncionarioException.DELETE, funcionario);
        }
    }

    @Override
    public void resetar() {
        this.funcionarios = new ArrayList<>();
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
        return obj;
    }
}
